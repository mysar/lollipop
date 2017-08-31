package io.github.mysar.blog.common.utils;

import org.apache.shiro.codec.Base64;

/**
 * Base64 加密解密工具
 */
public class Base64Util {

    /**
     * base64加密
     * @param str
     * @return
     */
    public static String encBase64(String str){
        return Base64.encodeToString(str.getBytes());
    }

    /**
     * base64解密
     * @param str
     * @return
     */
    public static String decBase64(String str){
        return Base64.decodeToString(str);
    }

    public static void main(String[] args) {
        String password="123456";
        System.out.println("Base64加密："+Base64Util.encBase64(password));
        System.out.println("Base64解密："+Base64Util.decBase64(Base64Util.encBase64(password)));
    }
}
