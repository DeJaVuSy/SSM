package com.zjp.generatemysql.service;

import com.zjp.generatemysql.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author jobob
 * @since 2022-01-05
 */
public interface ISysUserService extends IService<SysUser> {

    public SysUser userLogin(String name);

    public String getPassword(String username);

}
