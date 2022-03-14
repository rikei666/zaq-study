package com.zaqbest.study.domain;

import cn.hutool.http.HttpUtil;

public class DomainTest {
    public static void main(String[] args) {
        String domain = "baidu.com";
        long start = System.currentTimeMillis();
        String result = HttpUtil.get("https://api.ooomn.com/api/dm?domain="+domain);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(result);
    }
}
