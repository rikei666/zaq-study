package com.zaqbest.study.anquan;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 生成密码工具
 */
public class PasswdGenetor {

    public static void main(String[] args) {
        List<String> passwds = getPasswd_v2PlusPrefix();
        for (String s: passwds){
            System.out.println(s);
        }
        System.out.println(StrUtil.format("passwds size:{}", passwds.size()));
    }

    private static List<String> getPasswd_v2PlusPrefix() {
        List<String> prefixs = getPasswdPrefix();
        List<String> passwds = getPasswd_v2();

        List<String> combiles = new ArrayList<>();
        for (String prefix: prefixs){
            for(String pass: passwds){
                combiles.add(StrUtil.format("{}{}",prefix, pass));
            }
        }

        return combiles;
    }

    /**
     * 获取可能的密码表
     * 基于键盘数字的连续输入
     * @return
     */
    private static List<String> getPasswd_v2(){
        List<String> ret = new ArrayList<>();

        List<String> chars = new ArrayList<>();
        chars.addAll(StrUtil.split("`1234567890", ""));
        chars.addAll(StrUtil.split("~!@#$%^&*()", ""));

        //连续长度1-6位
        for (int len = 1; len <= 6; len++) {
            //正向连续，起始位0~11-6
            for (int begin = 0; begin <= 11 - len; begin++) {
                for (int i = 0; i < (1 << len); i++) {
                    String tmp = new String();
                    for (int j = 0; j < len; j++) {
                        tmp = tmp + chars.get(((1 << j) & i) != 0 ? 1 : 0).charAt(begin + j);
                    }
                    ret.add(tmp);
                }
            }

            //逆向连续，起始位置11-len~0
            for (int end = 10; end >= len; end--) {
                for (int i = 0; i < (1 << len); i++) {
                    String tmp = new String();
                    for (int j = 0; j < len; j++) {
                        tmp = tmp + chars.get(((1 << j) & i) != 0 ? 1 : 0).charAt(end - j);
                    }
                    ret.add(tmp);
                }
            }
        }

        //其他收集到的密码
        ret.add("123");
        ret.add("123!");
        ret.add("12=");
        ret.add("#000");
        ret.add("#999");
        ret.add("#888");
        ret.add("#111");
        ret.add("#222");
        ret.add("#222");
        ret.add("#555");
        ret.add("#444");
        ret.add("#777");
        ret.add("#333");
        ret.add("#890");
        ret.add("#123");
        ret.add("#1234");

        return ret;
    }

    //可能的用户名
    private static List<String> getLoginName(){
        return Arrays.asList("root", "jenkins", "tech_support", "appadmjjkj");
    }

    //可能的密码前缀
    private static List<String> getPasswdPrefix(){
        return Arrays.asList("root", "Juliang", "Admin", "juliang", "admin", "jenkins","Ju#Liang","Ju@Liang","fusionfin");
    }
}

