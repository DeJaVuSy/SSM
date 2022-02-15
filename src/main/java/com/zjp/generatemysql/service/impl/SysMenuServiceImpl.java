package com.zjp.generatemysql.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjp.generatemysql.entity.SysMenu;
import com.zjp.generatemysql.mapper.SysMenuMapper;
import com.zjp.generatemysql.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-02-11
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> queryMenu() {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper(new SysMenu());
        return sysMenuMapper.selectList(queryWrapper);
    }
}
