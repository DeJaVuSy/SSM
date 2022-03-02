package com.zjp.generatemysql.common.exception;

/**
 * @author AleeX
 * @Des 自定义异常
 * @date 2019/12/22 12:44 下午
 */
public class ExceptionUtils extends RuntimeException {

    private static final long serialVersionUID = 3593047729820617639L;

    private int type = -1;

    public int getType() {
        return type;
    }

    public ExceptionUtils(String msg) {
        super(msg);
    }

    public ExceptionUtils(String msg, int type) {
        super(msg);
        this.type = type;
    }
}
