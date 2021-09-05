package com.yingejiang.sportplay.controller;

import com.yingejiang.sportplay.bean.QueryInfo;
import com.yingejiang.sportplay.bean.User;
import com.yingejiang.sportplay.dao.UserDao;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    Logger logger = LoggerFactory.getLogger(UserController.class);

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

    @RequestMapping("/updatestate")
    public String updateUserState(@RequestParam("id") Integer id, @RequestParam("state") Boolean state){
        logger.info("更新状态：" + id + "_" + state);
        int result  = udao.updateState(id, state);
        logger.info(result+"");
        return result>0?"success":"error";
    }

    @RequestMapping("/adduser")
    public String addUser(@RequestBody User user){
        logger.info("要添加的用户信息：" + user);
        user.setRole("普通用户");
        user.setState(false);
        Integer result = udao.addUser(user);
        return result > 0 ? "success":"error";
    }

    @RequestMapping("/deleteuser")
    public String deleteUser(Integer id){
        logger.info("要删除的用户id：" + id);
        Integer result = udao.deleteUser(id);
        return result > 0? "success" : "error";
    }

    @RequestMapping("getuser")
    public Map<String, Object> getUser(Integer id){
        Map<String, Object> res = new HashMap<>();

        logger.info("要更改的用户编号：" + id);
        User user = udao.getUser(id);
        if(user != null){
            res.put("flag", "success");
            res.put("user", user);
            return res;
        }
        res.put("flag", "error");
        return res;
    }

    @RequestMapping("/edituser")
    public String editUser(@RequestBody User user){
        logger.info("更改的用户信息：" + user);
        Integer res = udao.editUser(user.getUsername(), user.getPassword(), user.getEmail());
        return res>0?"success":"error";
    }
}





























