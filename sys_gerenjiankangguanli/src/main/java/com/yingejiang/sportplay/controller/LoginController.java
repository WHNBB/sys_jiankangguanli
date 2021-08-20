package com.yingejiang.sportplay.controller;

import com.yingejiang.sportplay.bean.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yingejiang
 * @version V1.0
 * @Project: sportplay
 * @Package com.yingejiang.sportplay.controller
 * @Description: TODO
 * @date Date : 2021年06月21日 18:04
 */
@RestController
public class LoginController {
    @RequestMapping("test")
    public String test(){
        return "ok";
    }

    @RequestMapping("/login")
    public String login(@RequestBody User user){
        System.out.println(user);
        return "ok";
    }
}
