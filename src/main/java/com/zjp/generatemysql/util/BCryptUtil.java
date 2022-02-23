package com.zjp.generatemysql.util;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;

public class BCryptUtil {

    public static void main(String[] args) {
        String uuid= UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println("uuid===="+uuid);
        String pwd1 = md5("adminabc", "admin"+"7658a51a9c8f4cdfa3a9470f370ebe63");
        System.out.println("pwd1:"+pwd1);

//        String uuid1= UUID.randomUUID().toString().replaceAll("-", "");
//        System.out.println("uuid1===="+uuid1);
//        String pwd2 = md5("123456", "abc"+uuid1);
//        System.out.println(pwd2);

    }
    public static final String md5(String password, String salt){
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：相同密码使用不同的盐加密后的结果不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        //密码
        Object source = password;
        //加密次数
        int hashIterations = 2;
        SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
        return result.toString();
    }
}
