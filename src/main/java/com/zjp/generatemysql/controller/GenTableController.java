package com.zjp.generatemysql.controller;

import com.zjp.generatemysql.bean.ao.GenTabTableNameAO;
import com.zjp.generatemysql.bean.ao.GenTableAO;
import com.zjp.generatemysql.bean.vo.PageVO;
import com.zjp.generatemysql.entity.GenTable;
import com.zjp.generatemysql.service.IGenTableService;
import com.zjp.generatemysql.util.FileDelUtil;
import com.zjp.generatemysql.util.GenTableUtil;
import com.zjp.generatemysql.util.UserLoginToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 已生成的表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-12-07
 */
@Api(value = "代码生成接口")
@CrossOrigin //解决跨越问题
@RestController
@RequestMapping("/generatemysql/gen-table")
public class GenTableController {

    @Autowired
    private IGenTableService genTableService;

    @UserLoginToken //拦截请求，认证是否登录
    @ApiOperation("可生成实体类的表名")
    @PostMapping(value = "/ableGenTable" ,produces = "application/json")
    public PageVO<GenTable> genTable(@RequestBody GenTableAO genTableAO){
        System.out.println(genTableAO.toString());
//        List<GenTable> genTableList = genTableService.selectDbTableList(tableName,tableComment,startTime,endTime,currentPage,pageSize);
//        int count = genTableService.selectDbTableCount(tableName,tableComment,startTime,endTime);
        List<GenTable> genTableList = genTableService.selectDbTableList(genTableAO);
        int count = genTableService.selectDbTableCount(genTableAO);
        PageVO<GenTable> genTablePage = new PageVO(genTableList,count);
        return genTablePage;
    }

    @UserLoginToken //拦截请求，认证是否登录
    @ApiOperation("表数量")
    @PostMapping(value = "/selectDbTableCount" ,produces = "application/json")
    public int selectDbTableCount(String tableName,String tableComment,String startTime,String endTime){
        //int count = genTableService.selectDbTableCount(tableName,tableComment,startTime,endTime);
        //System.out.println("共："+count+"条数据");
        return 10;
    }

    @UserLoginToken //拦截请求，认证是否登录
    @ApiOperation("查询已生成的表")
    @PostMapping(value = "/queryGenTable" ,produces = "application/json")
    public List<GenTable> queryGenTable(){
        List<GenTable> genTableList = genTableService.queryGenTable();
        return genTableList;
    }

    @UserLoginToken //拦截请求，认证是否登录
    @ApiOperation("生成代码接口")
    @PostMapping(value = "/genTable" ,produces = "application/json")
    public boolean genTable(@RequestBody GenTabTableNameAO genTabTableNameAO){
        System.out.println(genTabTableNameAO.toString());
        //System.out.println(genTabTableNameAO.getMultipleTableName());
        GenTableUtil.GenTableSSM(genTabTableNameAO.getMultipleTableName());
        boolean isok = genTableService.insertGenTable(genTabTableNameAO.getListObject());
        return isok;
    }

    @UserLoginToken //拦截请求，认证是否登录
    @ApiOperation("删除类文件")
    @PostMapping(value = "/deleteClass" ,produces = "application/json")
    public boolean deleteClass(@RequestParam String className){
        FileDelUtil.deleteClass(className);
        boolean isok = genTableService.deleteGenTable(className);
        return isok;
    }


}
