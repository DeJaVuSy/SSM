package com.zjp.generatemysql.util;

import lombok.Data;

/**
 * @author zjp
 * @ClassName: ResultUtil
 * @Description: TODO
 * @date 2022\02\15 0024
 */
@Data
public class ResultUtil {

    public Integer code;

    public String msg;

    public Object data;

    public ResultUtil() {

    }

    public ResultUtil(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultUtil(Object data) {
        this.code = 200;
        this.msg = "操作成功";
        this.data = data;
    }

    public static ResultUtil build(Integer status, String msg, Object data) {
        return new ResultUtil(status, msg, data);
    }

    public static ResultUtil ok(Object data) {
        return new ResultUtil(data);
    }

    public static ResultUtil ok() {
        return new ResultUtil(null);
    }

    public static ResultUtil errorMsg(String msg) {
        return new ResultUtil(10001, msg, null);
    }

    public static ResultUtil errorMap(String msg) {
        return new ResultUtil(501, msg, null);
    }

    public static ResultUtil errorTokenMsg(String msg) {
        return new ResultUtil(502, msg, null);
    }

    public static ResultUtil errorException() {
        return new ResultUtil(10002, "服务器异常！", null);
    }

    public static ResultUtil error(Integer status, String msg) {
        return new ResultUtil(status,msg,null);
    }

}
