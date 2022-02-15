package com.zjp.generatemysql.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class Base64Util {

    public static void main(String[] args) throws IOException {
//        String broforePWD="password";
//        System.out.println("加密前:"+broforePWD);
//
//        String afterPWD = encryptBASE64(broforePWD.getBytes());
//        System.out.println("加密后:"+afterPWD);
//
//        byte[] bytes = decryptBASE64("x4h7ABpd7CDEjc4FGH");
//        System.out.println("解密后:"+new String(bytes));

        byte[] bytes2 = decryptBASE64("VjJ4ak1WWnJNVWhWYkdoVFlsZDRZVmxzV2t0TmJHUlpZMFZhYkZZd05ESldNbkJQWVZaYVJrMUlaRlZpUmtwaFZVWkZPVkJSUFQwPQ==");
        for (int i = 0; i < 4;i++){
            bytes2 = decryptBASE64(new String(bytes2));
        }
        System.out.println("解密后:"+new String(bytes2));

        System.out.println("解密后:"+new String(decryptBASE64("NFXlWUm1oVFlsZDRjRnBYZEV0a1JtUlZVMnhPYVZKVVVqVlZiR2gzWVRGWmVHTklWVDA9")));

        System.out.println(decryptBASE64Five("VmtSQ2EwMHdNVWRpU0ZKVFlsZDRhRlpxU210T1JtUlpZMFZhYkZZd1drbFVNV1J6VkcxV2MxSllhRlZpVjAwMVZVWkZPVkJSUFQwPQ=="));

    }

    /*加密*/
    public static String encryptBASE64(byte[] pwd){
        String  result= new BASE64Encoder().encodeBuffer(pwd);
        return result;
    }
    /*解密*/
    public static byte[] decryptBASE64(String pwd) throws IOException {
        byte[] bytes = new BASE64Decoder().decodeBuffer(pwd);
        return bytes;
    }

    /*解密5*/
    public static String decryptBASE64Five(String pwd) {
        byte[] bytes2 = new byte[0];
        try {
            bytes2 = decryptBASE64(pwd);
            for (int i = 0; i < 4;i++){
                bytes2 = decryptBASE64(new String(bytes2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String PwdSalt = new String(bytes2);
        PwdSalt = PwdSalt.substring(4,6)+PwdSalt.substring(9,12)+PwdSalt.substring(16,PwdSalt.length());
        return PwdSalt;
    }
}
