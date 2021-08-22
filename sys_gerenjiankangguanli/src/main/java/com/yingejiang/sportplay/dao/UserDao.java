package com.yingejiang.sportplay.dao;

import com.yingejiang.sportplay.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
    public User getUserByMessage(@Param("username") String username, @Param("password") String password);
}
