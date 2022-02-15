package com.zjp.generatemysql.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zjp
 * @ClassName: ResultUtil
 * @Description: TODO
 * @date 2022\02\15 0024
 */
@Data
public class ResultUtil<T> {

    public String code;

    public String message;

    public T data;

}
