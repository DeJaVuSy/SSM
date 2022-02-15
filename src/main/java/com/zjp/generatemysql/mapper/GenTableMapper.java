package com.zjp.generatemysql.mapper;

import com.zjp.generatemysql.bean.ao.GenTableAO;
import com.zjp.generatemysql.entity.GenTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 已生成的表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2021-12-07
 */
public interface GenTableMapper extends BaseMapper<GenTable> {

    public List<GenTable> selectDbTableList(GenTableAO genTableAO);

    public int selectDbTableCount(GenTableAO genTableAO);

    public int addGenTableBatch(List<GenTable> genTableList);

}
