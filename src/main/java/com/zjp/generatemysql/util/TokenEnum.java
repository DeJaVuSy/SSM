package com.zjp.generatemysql.util;

public enum TokenEnum {

    EXPIRATION("time",30 * 60 * 1000);


    private String key;

    private int value;

    TokenEnum(String key,int value){
        this.key = key;
        this.value = value;
    }
}
