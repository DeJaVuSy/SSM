package com.zjp.generatemysql.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 已生成的表
 * </p>
 *
 * @author jobob
 * @since 2021-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GenTable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    private Long tableId;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 实体类名称
     */
    private String className;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;


}
