package io.github.mysar.blog.common.utils;

import org.apache.shiro.codec.Base64;

/**
 * Base64 加密解密工具
 * Created by Im.Yan on 2017/8/27.
 */
public class Base64Utils {

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
        System.out.println("Base64加密："+ Base64Utils.encBase64(password));
        System.out.println("Base64解密："+ Base64Utils.decBase64(Base64Utils.encBase64(password)));

        // 查看使用MD5混肴加密后的密码
        System.out.println("admin加密后是:"+Md5Util.pwdDigest("admin"));
    }
}
