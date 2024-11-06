package com.paul.project.constant;

public interface RedisConstant {

    /**
     * 用户签到记录的 Redis Key前缀
     */
    String USER_SIGN_IN_REDIS_KEY_PREFIX = "user:signIns:";

    //静态方法 获取用户签到记录的redis key

    /**
     * 获取用户签到记录
     * @param year
     * @param userId
     * @return
     */
    static String getUserSignInRedisKey(int year, long userId){
        return String.format("%s:%s:%s",USER_SIGN_IN_REDIS_KEY_PREFIX,year,userId);
    }
}
