package com.zjp.generatemysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 班级信息表
 * </p>
 *
 * @author jobob
 * @since 2022-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EduClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级ID
     */
    @TableId(value = "class_id", type = IdType.AUTO)
    private Long classId;

    /**
     * 班级名
     */
    private String className;

    /**
     * 开班时间
     */
    @TableField("class_Date")
    private LocalDate classDate;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 班级专业id
     */
    @TableField("class_Major")
    private Long classMajor;


}
