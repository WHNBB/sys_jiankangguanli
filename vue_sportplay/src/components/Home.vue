<template>
  <el-container class="home-container">
    <!-- 头部 -->
    <el-header>
      <div>
        <img src="../assets/logo.png" alt="" />
        <span>个人运动管理</span>
      </div>
      <el-button type="info" @click="logout">安全退出</el-button>
    </el-header>
    <!-- 中间区域 -->
    <el-container>
      <!-- 侧边 -->
      <el-aside :width="isCollapse?'64px':'200px'">
          <div class="toggle-button" @click="toggleCollapase">|||</div>
          <!-- el-menu 中加上 unique-opened 可以保持只有一个菜单栏开启，另一个自动关闭 -->
          <el-menu background-color="#545c64" text-color="#fff" active-text-color="#409eff" 
          :collapse="isCollapse" :collapse-transition="false" :router="true" :default-active="activePath">
            <!-- collapse-transition 伸缩的方向 -->
            <!-- 一级菜单 -->
            <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">
              <template slot="title">
                <i :class="iconsObject[item.id]"></i>
                <span>{{item.title}}</span>
              </template>
              <!-- 二级菜单 -->
              <el-menu-item :index="it.path" v-for="it in item.sList" :key="it.id" @click="savePath(it.path)">
                <template slot="title">
                  <i :class="iconsObject[it.id]"></i>
                  <span>{{it.title}}</span>
                </template>
              </el-menu-item>
            </el-submenu>
          </el-menu>
      </el-aside>
      <!-- 内容 -->
      <el-main>
          <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data(){
      return{
          menuList:[], // 导航栏信息
          isCollapse: false, // 控制导航栏是否伸缩 true=伸缩，false=不伸
          iconsObject:{
             "100":"iconfont icon-guanliyuan1",
             "101":"iconfont icon-yonghuliebiao",
             "102":"iconfont icon-quanxian",
             "103":"iconfont icon-yundong2",
             "104":"iconfont icon-shangpin1",
             "200":"iconfont icon-yundong1",
             "201":"iconfont icon-shu",
             "202":"iconfont icon-qialuli",
             "203":"iconfont icon-yingyangke"
          }, // 左侧导航栏图标
          activePath:'/welcome',
      }
  },
  created(){
      // 获取导航栏信息
      this.getMenuList();
      // 获取登录后保存的路径
      this.activePath = window.sessionStorage.getItem("activePath");
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    getMenuList(){
        this.$http.get(
            '/getmenus'
        ).then(res=>{
            // vue接收后端返回的值，取值时，点的时候，与后端返回的存储数据的变量名没有关系
            // 有关系的是map中键的名字
            // 后端返回的数据在res的data中，剩下的就是map中的键值对了
            // 想输出json请求得到的数据时，单独输出，不要用 + 号做连接处理，否则会显示 xxx:object
            // 因为有 + 号时，会先用toString()方法将字符串拼接
            let receive = res.data;
            console.log("home请求导航栏信息成功");
            console.log("receive：");
            console.log(receive.menus);
            this.menuList = receive.menus;
        }).catch(error=>{
            console.log("home请求导航栏信息错误");
        });
        console.log("getMenuList");
    },
    toggleCollapase(){
      this.isCollapse = !this.isCollapse;
    },
    savePath(path){
      window.sessionStorage.setItem("activePath",path);
      this.activePath = path;
    }
  },
};
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}
// 头部
.el-header {
  background-color: #373d41;
  color: #fff;
  font-size: 20px;
  text-align: center;
  align-items: center;
  // 设置左侧外边距
  padding-left: 0%;
  //
  display: flex;
  // 两侧居中
  justify-content: space-between;
  > div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
}
// 侧边
.el-aside {
  background-color: #333744;
  color: #333;
  .el-menu {
    border-right: none;
  }
}
// 内容
.el-main {
  background-color: #eaedf1;
  color: #333;
}
// 因为图片太大，所以我想调整图片
img {
  width: 55px;
  height: 55px;
}
// 按钮
.toggle-button {
  background-color: #4A5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>