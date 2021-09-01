package com.yingejiang.sportplay.controller;

import com.yingejiang.sportplay.bean.QueryInfo;
import com.yingejiang.sportplay.bean.User;
import com.yingejiang.sportplay.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
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
 * @date Date : 2021年09月01日 19:59
 */
@RestController
public class UserController {
    @Autowired
    private UserDao udao;

    @RequestMapping("/alluser")
    // 当有前端发送参数到后台时，要加上RequestBody注解
    public Map<String, Object> getUsers(@RequestBody QueryInfo queryInfo){

        System.out.println("开始查找用户信息");
        Map<String, Object> re_users = new HashMap<>();

        // 获取数据总条数
        // 模糊查询的用法
        int numbers = udao.getCount("%"+queryInfo.getQuery()+"%");
        // 当前页起始号
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();

        // 查询用户
        List<User> users = udao.getUsers("%"+queryInfo.getQuery()+"%", pageStart, queryInfo.getPageSize());

        re_users.put("numbers", numbers);
        re_users.put("data",users);
        System.out.println(re_users);
        return re_users;
    }
}





























