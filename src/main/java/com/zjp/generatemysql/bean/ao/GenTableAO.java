package com.zjp.generatemysql.bean.ao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class GenTableAO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

    private String startTime;//开始日期

    private String endTime;//结束日期

    private int currentPage;//当前页

    private int pageSize;//显示条数
}
