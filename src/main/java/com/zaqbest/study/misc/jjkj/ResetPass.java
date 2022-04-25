package com.zaqbest.study.misc.jjkj;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 老项目 密码算法
 */
public class ResetPass {
    private static final int SALT_SIZE = 16;
    private static final int HASH_INTERATIONS = 1024;

    public static void main(String[] args) {
        String pass=  "123456";

        byte[] salt = RandomUtil.randomBytes(SALT_SIZE);
        String saltHexStr = HexUtil.encodeHexStr(salt);

        byte[] hashPassword =
                Digests.sha512(pass.getBytes(), salt, HASH_INTERATIONS);
        String passHexStr = HexUtil.encodeHexStr(hashPassword);

        System.out.println("salt: " + saltHexStr);
        System.out.println("pass: " + passHexStr);
    }
}
