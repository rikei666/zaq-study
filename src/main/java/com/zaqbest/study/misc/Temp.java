package com.zaqbest.study.misc;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class Temp {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Snowflake snowflake = IdUtil.getSnowflake(1,1);
            System.out.println("ACCOUNT_" + snowflake.nextIdStr());
        }
    }
}
