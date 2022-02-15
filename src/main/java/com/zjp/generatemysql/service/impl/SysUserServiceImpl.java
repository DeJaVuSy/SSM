package com.zjp.generatemysql.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjp.generatemysql.entity.SysUser;
import com.zjp.generatemysql.mapper.SysUserMapper;
import com.zjp.generatemysql.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjp.generatemysql.util.BCryptUtil;
import com.zjp.generatemysql.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-01-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser userLogin(String name) {
        SysUser sysUser = new SysUser();
        sysUser.setLoginName(name);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>(sysUser);
        List<SysUser> selectList = sysUserMapper.selectList(queryWrapper);
        if(selectList.size() > 0){
            return selectList.get(0);
        }
        return null;
    }

    @Override
    public String getPassword(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setLoginName(username);
        String getPassword = sysUserMapper.selectList(new QueryWrapper<>(sysUser)).get(0).getPassword();
        return getPassword;
    }
}
