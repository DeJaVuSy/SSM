package com.zjp.generatemysql.bean.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PageVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 总数
     */
    private int total;

    private List<T> listObject;

    public PageVO(List<T> listObject, int total){
        this.listObject = listObject;
        this.total = total;
    }


}
