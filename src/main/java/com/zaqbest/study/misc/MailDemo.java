package com.zaqbest.study.misc;

import cn.hutool.extra.mail.MailUtil;

public class MailDemo {
    public static void main(String[] args) {
        MailUtil.send("lipan.me@qq.com", "测试", "邮件来自Hutool测试", false);
    }
}
