package com.yingejiang.sportplay.bean;

import java.util.List;

/**
 * @author : yingejiang
 * @version V1.0
 * @Project: sys_gerenjiankangguanli
 * @Package com.yingejiang.sportplay.bean
 * @Description: TODO
 * @date Date : 2021年08月22日 19:07
 */

// 主导航
public class MainMenu {
    private int id;
    private String title;
    private String path;
    private List<SubMenu> sList;

    public MainMenu() {
    }

    public MainMenu(String title, String path, List<SubMenu> sList) {
        this.title = title;
        this.path = path;
        this.sList = sList;
    }

    @Override
    public String toString() {
        return "MainMenu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", sList=" + sList +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<SubMenu> getsList() {
        return sList;
    }

    public void setsList(List<SubMenu> sList) {
        this.sList = sList;
    }
}
