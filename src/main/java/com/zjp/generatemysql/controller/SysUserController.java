package com.zjp.generatemysql.controller;
import com.zjp.generatemysql.entity.SysUser;
import com.zjp.generatemysql.service.ISysUserService;
import com.zjp.generatemysql.util.BCryptUtil;
import com.zjp.generatemysql.util.Base64Util;
import com.zjp.generatemysql.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2022-01-05
 */
@Api(value = "用户")
@CrossOrigin //解决跨越问题
@RestController
@RequestMapping("/generatemysql/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation("登录")
    @PostMapping(value = "/login" ,produces = "application/json")
    public Map<String, Object> userLogin(String name, String pwd){
        //JSONObject jsonObject=new JSONObject();
        Map<String, Object> map = new HashMap<>();
        SysUser sysUser = sysUserService.userLogin(name);
        if(sysUser == null){
            map.put("code", "0");
            map.put("message","error");
        }else {
            String sysUserPassword = sysUser.getPassword();
            //加密 salt= name + 数据库中查出来的Salt
//            System.out.println("密码："+Base64Util.decryptBASE64Five(pwd));
//            System.out.println("盐："+name+sysUser.getSalt());
            //解码、md5加密撒盐
            String LoginUserPassword = BCryptUtil.md5(Base64Util.decryptBASE64Five(pwd),name+sysUser.getSalt());
//            System.out.println("pwd:"+Base64Util.decryptBASE64Five(pwd));
//            System.out.println("salt："+name+sysUser1.getSalt());
            System.out.println("密码："+sysUserPassword);
            System.out.println("密码："+LoginUserPassword);
            //从数据库中查出来的密码对比是否相同
            //return LoginUserPassword.equals(sysUserPassword)?true:false;
            if(LoginUserPassword.equals(sysUserPassword)){
                // 验证成功后发送token
                String token = JwtUtil.sign(name,LoginUserPassword);
                System.out.println("token"+token);
                map.put("code", "1");
                map.put("message","success");
                map.put("token", token);
            }else {
                map.put("code", "0");
                map.put("message","error");
            }
        }
        return map;
    }

}
