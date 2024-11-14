package com.paul.project.manager;


import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.IntegerCodec;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CounterManager {

    @Resource
    private RedissonClient redisson;

    public long incrAndGetCounter(String key) {
        return incrAndGetCounter(key, 1, TimeUnit.MINUTES);
    }

    public long incrAndGetCounter(String key, int timeInterval, TimeUnit timeUnit) {
        int expirationTimeInSeconds;
        switch (timeUnit) {
            case SECONDS:
                expirationTimeInSeconds = timeInterval;
                break;
            case MINUTES:
                expirationTimeInSeconds = timeInterval * 60;
                break;
            case HOURS:
                expirationTimeInSeconds = timeInterval * 3600;
                break;
            default:
                throw new IllegalArgumentException("不支持该单位");
        }
        return incrAndGetCounter(key, timeInterval, timeUnit, expirationTimeInSeconds);
    }


    public long incrAndGetCounter(String key, int timeInterval, TimeUnit timeUnit, long expirationTimeInSeconds) {
        if (StrUtil.isBlank(key)) {
            return 0;
        }

        //根据时间粒度生成redis key
        long timeFactor;
        switch (timeUnit) {
            case SECONDS:
                timeFactor = Instant.now().getEpochSecond() / timeInterval;
                break;
            case MINUTES:
                timeFactor = Instant.now().getEpochSecond() / timeInterval / 60;
                break;
            case HOURS:
                timeFactor = Instant.now().getEpochSecond() / timeInterval / 3600;
                break;
            default:
                throw new IllegalArgumentException("不支持该单位");
        }
        String redisKey = key + ":" + timeFactor;

        //Lua脚本
        String luaScript =
                "if redis.call('exists', KEYS[1]) == 1 then " +
                        "  return redis.call('incr', KEYS[1]); " +
                        "else " +
                        "  redis.call('set', KEYS[1], 1); " +
                        "  redis.call('expire', KEYS[1], ARGV[1]); " +
                        "  return 1; " +
                        "end";

        //执行lua
        RScript script = redisson.getScript(IntegerCodec.INSTANCE);
        Object countObj = script.eval(
                RScript.Mode.READ_WRITE,
                luaScript,
                RScript.ReturnType.INTEGER,
                Collections.singletonList(redisKey), expirationTimeInSeconds
        );
        return (long) countObj;


    }
}
