package com.yingejiang.sportplay.controller;

import com.yingejiang.sportplay.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : yingejiang
 * @version V1.0
 * @Project: sys_gerenjiankangguanli
 * @Package com.yingejiang.sportplay.controller
 * @Description: TODO
 * @date Date : 2021年08月22日 20:49
 */
@RestController
public class MenuController {

    @Autowired
    MenuDao menuDao;

    @RequestMapping("getmenus")
    public Map<String, Object> getMenus(){
        System.out.println("开始查找导航栏信息");
        HashMap<String, Object> returndata = new HashMap<>();
        List<MenuDao> menus = menuDao.getMenus();
        // status 成功标识符 错误==404  成功==200
        System.out.println("menus："+ menus);
        if(menus != null){
            returndata.put("menus", menus);
            returndata.put("status", 200);
        }else{
            // 因为没有查到导航栏信息，所以没必要添加到map中，只添加404报错信息即可
            returndata.put("status", 404);
        }
        return returndata;
    }
}
