package com.zjp.generatemysql.bean.ao;

import com.zjp.generatemysql.entity.GenTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class GenTabTableNameAO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String multipleTableName;

    private List<T> listObject;
}
