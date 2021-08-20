package com.yingejiang.sportplay.bean;

/**
 * @author : yingejiang
 * @version V1.0
 * @Project: sportplay
 * @Package com.yingejiang.sportplay.bean
 * @Description: TODO
 * @date Date : 2021年06月23日 16:41
 */
/*
* 用户实体
* */
public class User {
    private int id;
    private String username; // 用户名
    private String password; // 密码
    private String email; // 邮箱
    private String role; // 角色
    private boolean state; // 状态

    public User() {
    }

    public User(String username, String password, String email, String role, boolean state) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", state=" + state +
                '}';
    }
}
