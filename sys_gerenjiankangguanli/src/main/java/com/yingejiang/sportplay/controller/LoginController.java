package com.yingejiang.sportplay.controller;

import com.yingejiang.sportplay.bean.User;
import com.yingejiang.sportplay.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yingejiang
 * @version V1.0
 * @Project: sportplay
 * @Package com.yingejiang.sportplay.controller
 * @Description: TODO
 * @date Date : 2021年06月21日 18:04
 */
// 加上RestController注解后，可自动将返回前端的Map数据转换为json格式
@RestController
public class LoginController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/login")
    public Map<String, Object> login(@RequestBody User user){
        String flag = "error";
        HashMap<String, Object> res = new HashMap<>();

        User returnUser = userDao.getUserByMessage(user.getUsername(), user.getPassword());
        System.out.println("user："+returnUser);

        if(returnUser != null){
            flag = "ok";
        }

        res.put("flag",flag);
        res.put("user",returnUser);
        return res;
    }

}
