package com.yingejiang.sportplay.dao;

import com.yingejiang.sportplay.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : yingejiang
 * @version V1.0
 * @Project: sys_gerenjiankangguanli
 * @Package com.yingejiang.sportplay.dao
 * @Description: TODO
 * @date Date : 2021年08月22日 15:27
 */

@Repository
public interface UserDao {
    // 查询用户的方法
    public User getUserByMessage(@Param("username") String username,
                                 @Param("password") String password);
    // 查询所有用户的方法，或模糊查询的方法
    public List<User> getUsers(@Param("username") String username,
                               @Param("pageStart") int pageStart,
                               @Param("pageSize") int pageSize);

    // 查询用户数量的方法
    public int getCount(@Param("username") String username);
}
