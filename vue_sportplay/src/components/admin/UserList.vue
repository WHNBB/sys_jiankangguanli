<template>
  <div>
    用户列表
    <!-- 导航栏开发，面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 用户列表主体 -->
    <el-card>
      <el-row :gutter="25"> <!-- :gutter="25" 列间隙 -->
        <el-col :span="10"> <!-- :span 列宽 -->
          <!-- 搜索区域 -->
          <el-input placeholder="请输入要搜索的内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <!-- 搜索用户按钮 -->
          <el-button type="primary">添加用户</el-button>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
    data(){
        return{

        }
    },
    methods:{
        getUserList(){
            console.log(123456);
        },
    },
    created(){
        this.getUserList();
    }
};
</script>

<style lang="less" scoped>
// 导航栏样式
.el-breadcrumb {
  margin-bottom: 15px;
  font-size: 17px;
}
</style>