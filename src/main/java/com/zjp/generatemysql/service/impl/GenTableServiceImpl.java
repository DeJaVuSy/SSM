package com.zjp.generatemysql.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjp.generatemysql.bean.ao.GenTableAO;
import com.zjp.generatemysql.entity.GenTable;
import com.zjp.generatemysql.mapper.GenTableMapper;
import com.zjp.generatemysql.service.IGenTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjp.generatemysql.util.GenTableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 已生成的表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-12-07
 */
@Service
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable> implements IGenTableService {

    @Autowired
    private GenTableMapper genTableMapper;


    //查询可生成代码的表
    @Override
    public List<GenTable> selectDbTableList(GenTableAO genTableAO) {
        genTableAO.setCurrentPage((genTableAO.getCurrentPage()-1)*genTableAO.getPageSize());//计算分页
        List<GenTable> genTableList = genTableMapper.selectDbTableList(genTableAO);
        for (GenTable genTable:genTableList) {
            //设置实体类名
            genTable.setClassName(GenTableUtil.convertToCamelCase(genTable.getTableName()));
        }
        return genTableList;
    }

    //查询数量
    @Override
    public int selectDbTableCount(GenTableAO genTableAO) {
        return genTableMapper.selectDbTableCount(genTableAO);
    }

    //查询生成的表
    @Override
    public List<GenTable> queryGenTable() {
        return genTableMapper.selectList(new QueryWrapper<>());
    }

    //新增生成的表
    @Override
    public boolean insertGenTable(List<GenTable> genTableList) {
        boolean isok = genTableMapper.addGenTableBatch(genTableList)>0?true:false;
        return isok;
    }

    @Override
    public boolean deleteGenTable(String className) {
        GenTable genTable = new GenTable();
        genTable.setClassName(className);
        return genTableMapper.delete(new QueryWrapper<>(genTable))>0?true:false;
    }

//    @Override
//    public List<GenTable> selectDbTableList(GenTable genTable) {
//        return genTableMapper.selectById(genTable);
//    }
}
