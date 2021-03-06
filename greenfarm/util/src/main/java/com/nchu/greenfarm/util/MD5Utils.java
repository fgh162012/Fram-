package com.nchu.greenfarm.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName SecondMenuController
 * @Description: java类作用描述
 * @Author: 3162748949fgh
 * @CreateDate: 2019/1/6 10:54
 * @UpdateUser: 3162748949fgh
 * @UpdateDate: 2019/1/6 10:54
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 **/
public  class MD5Utils {
    /**
     * 使用md5的算法进行加密
     */
    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// // 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
