<template>
  <div>
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
      <!-- border 边框 stripe隔行变色 -->
      <el-table :data="userList" border stripe>
        <el-table-column type="index"></el-table-column> <!--索引列-->
        <el-table-column label="用户名" prop="username"></el-table-column>
        <el-table-column label="邮箱" prop="email"></el-table-column>
        <el-table-column label="密码" prop="password"></el-table-column>
        <el-table-column label="角色" prop="role"></el-table-column>
        <el-table-column label="状态" prop="state">
          <!-- 作用域插槽 -->
          <template slot-scope="scope">
            <!-- {{scope.row}} 每一行封存的数据 -->
            <!-- scope.row.state 取出每一行封存数据的状态码 -->
            <el-switch v-model="scope.row.state"></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" min-width="120px">
          <template>
            <!-- 修改 -->
            <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
            <!-- 删除 按钮的写法 -->
            <el-button type="danger" size="mini" icon="el-icon-delete"></el-button>
            <!-- 权限 -->
            <!-- :enterable="false" 为false时，文字提示隐藏 -->
            <el-tooltip effect="dark" content="分配权限" placement="top-start" :enterable="false">
              <el-button type="warning" size="mini" icon="el-icon-setting"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <div>
        <!-- 分页组件的使用 -->
        <!-- 关键点，当前页数，每页显示条数 -->
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pageNum"
          :page-sizes="[1, 2, 5, 100]"
          :page-size="queryInfo.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
    data(){
        return{
          queryInfo: {
            query:"",
            pageNum: 1,
            pageSize: 5
            // 因为分页了，所以才返回前5条数据
          },
          userList: [],
          total:0,
        }
    },
    methods:{
        getUserList(){
            this.$http.post(
              // 路径前边的斜杠可写可不写，发送参数到后台时
              "/alluser",
              this.queryInfo
            ).then(res=>{
              let receiver = res.data;
              this.userList = receiver.data;
              this.total = receiver.numbers;
              console.log(receiver);
            }).catch(error=>{
               console.log(error);
            });
        },
        handleSizeChange(pageSize){
          this.queryInfo.pageSize = pageSize;
          this.getUserList();
        },
        handleCurrentChange(pageNum){
          this.queryInfo.pageNum = pageNum;
          this.getUserList();
        }
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