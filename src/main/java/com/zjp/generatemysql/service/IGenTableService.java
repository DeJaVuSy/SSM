package com.zjp.generatemysql.service;

import com.zjp.generatemysql.bean.ao.GenTableAO;
import com.zjp.generatemysql.entity.GenTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 已生成的表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-12-07
 */
public interface IGenTableService extends IService<GenTable> {

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
//    public List<GenTable> selectDbTableList(GenTable genTable);

    /**
     * 查询可生成的表
     * @return
     */
    public List<GenTable> selectDbTableList(GenTableAO genTableAO);

    /**
     * 查询GenTable数量
     * @return
     */
    public int selectDbTableCount(GenTableAO genTableAO);

    /**
     * 查询GenTable已生成的表
     * @return
     */
    public List<GenTable> queryGenTable();

    /**
     * 新增成功生成的表数据
     * @param genTableList
     * @return
     */
    public boolean insertGenTable(List<GenTable> genTableList);

    /**
     * 删除类文件
     * @param className
     * @return
     */
    public boolean deleteGenTable(String className);

}
