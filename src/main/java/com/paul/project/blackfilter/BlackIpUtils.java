package com.paul.project.blackfilter;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.yaml.snakeyaml.Yaml;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class BlackIpUtils {

    private static BitMapBloomFilter bloomFilter;

    //判断IP是否在黑名单
    public static boolean isBlackIp(String ip) {
        return bloomFilter.contains(ip);
    }

    //重建IP黑名单
    public static void rebuildBlackIp(String configInfo) {
        if (StrUtil.isBlank(configInfo)) {
            configInfo = "{}";
        }
        Yaml yaml = new Yaml();
        Map map = yaml.loadAs(configInfo, Map.class);
        List<String> blackIps = (List<String>) map.get("blackIps");
        if(CollUtil.isNotEmpty(blackIps)){
            BitMapBloomFilter bitMapBloomFilter = new BitMapBloomFilter(958506);
            for (String blackIp : blackIps) {
                bitMapBloomFilter.add(blackIp);
            }
            bloomFilter = bitMapBloomFilter;
        }else {
            bloomFilter = new BitMapBloomFilter(100);
        }
    }
}
