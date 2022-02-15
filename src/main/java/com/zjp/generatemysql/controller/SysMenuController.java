package com.zjp.generatemysql.controller;


import com.zjp.generatemysql.entity.SysMenu;
import com.zjp.generatemysql.service.ISysMenuService;
import com.zjp.generatemysql.util.JwtUtil;
import com.zjp.generatemysql.util.UserLoginToken;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2022-02-11
 */
@RestController
@CrossOrigin //解决跨越问题
@RequestMapping("/generatemysql/sys-menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    //@UserLoginToken //拦截请求，认证是否登录
    @ApiOperation("菜单查询")
    @PostMapping( value = "/queryMenu",produces = "application/json")
    public List<SysMenu> queryMenu(){
//        String toke = httpServletRequest.getHeader("token");
//        String username = JwtUtil.getUserNameByToken(toke);

        return sysMenuService.queryMenu();

    }
}
