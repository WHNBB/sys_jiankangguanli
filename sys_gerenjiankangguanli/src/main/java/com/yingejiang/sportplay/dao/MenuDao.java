package com.yingejiang.sportplay.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : yingejiang
 * @version V1.0
 * @Project: sys_gerenjiankangguanli
 * @Package com.yingejiang.sportplay.dao
 * @Description: TODO
 * @date Date : 2021年08月22日 19:19
 */
@Repository
public interface MenuDao {
    public List<MenuDao> getMenus();
}
