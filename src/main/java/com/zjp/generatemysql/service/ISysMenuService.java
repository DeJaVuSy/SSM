package com.zjp.generatemysql.service;

import com.zjp.generatemysql.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author jobob
 * @since 2022-02-11
 */
public interface ISysMenuService extends IService<SysMenu> {

    public List<SysMenu> queryMenu();

}
