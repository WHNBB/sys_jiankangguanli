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
      <el-aside width="200px">
          <!-- el-menu 中加上 unique-opened 可以保持只有一个菜单栏开启，另一个自动关闭 -->
          <el-menu @open="handleOpen" @close="handleClose" background-color="#545c64" text-color="#fff"
            active-text-color="#409eff" >
            <!-- 一级菜单 -->
            <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>{{item.title}}</span>
              </template>
              <!-- 二级菜单 -->
              <el-menu-item :index="it.id+''" v-for="it in item.sList" :key="it.id">
                <template slot="title">
                  <i class="el-icon-location"></i>
                  <span>{{it.title}}</span>
                </template>
              </el-menu-item>
            </el-submenu>
          </el-menu>
      </el-aside>
      <!-- 内容 -->
      <el-main>
          {{menuList}}
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data(){
      return{
          menuList:[], // 导航栏信息
      }
  },
  created(){
      // 获取导航栏信息
      this.getMenuList();
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
            console.log("receive：");
            console.log(receive.menus);
            this.menuList = receive.menus;
        }).catch(error=>{
            console.log("Home.vue：" + error);
        });
        console.log("getMenuList");
    },
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
</style>