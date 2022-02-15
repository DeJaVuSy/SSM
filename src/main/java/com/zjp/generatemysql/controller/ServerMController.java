package com.zjp.generatemysql.controller;

import com.zjp.generatemysql.entity.domain.Server;
import com.zjp.generatemysql.util.UserLoginToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 服务器监控
 * </p>
 *
 * @author jobob
 * @since 2022-01-18
 */
@Api(value = "服务器监控")
@CrossOrigin //解决跨越问题
@RestController
@RequestMapping("/generatemysql")
public class ServerMController {


    @ApiOperation("服务器监控")
    @PostMapping(value = "ServerM",produces = "application/json")
    public Server ServerM(){
        System.out.println("服务器监控查询");
        Server server = new Server();
        try {
            server.copyTo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return server;
    }
}
