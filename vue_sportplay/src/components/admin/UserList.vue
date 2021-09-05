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
      <el-row :gutter="25">
        <!-- :gutter="25" 列间隙 -->
        <el-col :span="10">
          <!-- :span 列宽 -->
          <!-- 搜索区域 -->
          <el-input
            placeholder="请输入要搜索的内容"
            v-model="queryInfo.query"
            clearable
            @clear="getUserList"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getUserList"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <!-- 搜索用户按钮 -->
          <el-button type="primary" @click="dialogShow = true">添加用户</el-button>
        </el-col>
      </el-row>
      <!-- border 边框 stripe隔行变色 -->
      <el-table :data="userList" border stripe>
        <el-table-column type="index"></el-table-column>
        <!--索引列-->
        <el-table-column label="用户名" prop="username"></el-table-column>
        <el-table-column label="邮箱" prop="email"></el-table-column>
        <el-table-column label="密码" prop="password"></el-table-column>
        <el-table-column label="角色" prop="role"></el-table-column>
        <el-table-column label="状态" prop="state">
          <!-- 作用域插槽 -->
          <template slot-scope="scope">
            <!-- {{scope.row}} 每一行封存的数据 -->
            <!-- scope.row.state 取出每一行封存数据的状态码 -->
            <el-switch
              v-model="scope.row.state"
              @change="updateState(scope.row)"
            ></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" min-width="120px">
          <template slot-scope="scope">
            <!-- 修改 -->
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
              @click="deleteDialogShow = true, getUser(scope.row.id)"
            ></el-button>
            <!-- 删除 按钮的写法 -->
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-delete"
              @click="deleteUser(scope.row.id)"
            ></el-button>
            <!-- 权限 -->
            <!-- :enterable="false" 为false时，文字提示隐藏 -->
            <el-tooltip
              effect="dark"
              content="分配权限"
              placement="top-start"
              :enterable="false"
            >
              <el-button
                type="warning"
                size="mini"
                icon="el-icon-setting"
              ></el-button>
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
          :total="total"
        >
        </el-pagination>
      </div>
    </el-card>
    <!-- 新增用户区域 -->
    <el-dialog title="新增用户" :visible.sync='dialogShow' width="50%" @close="dialogClosed">
      <el-form :model="addUser" :rules="addUserRules" ref="addUserRef" label-width="70px">
        <!-- 用户名 -->
        <el-form-item label="用户名" prop="username"> <!-- prop什么意思 -->
          <el-input v-model="addUser.username"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="密码" prop="password"> <!-- prop什么意思 -->
          <el-input v-model="addUser.password"></el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item label="邮箱" prop="email"> <!-- prop什么意思 -->
          <el-input v-model="addUser.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer"> 
        <el-button @click="dialogShow = false">取消</el-button>
        <el-button type="primary" @click="addUserForm">确定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑用户区域 -->
    <el-dialog title="新增用户" :visible.sync='deleteDialogShow' width="50%" @close="deleteDialogClosed">
      <el-form :model="editUser" :rules="editUserRules" ref="editUserRef" label-width="70px">
        <!-- 用户名 -->
        <el-form-item label="用户名" prop="username"> <!-- prop什么意思 -->
          <el-input v-model="editUser.username" disabled></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="密码" prop="password"> <!-- prop什么意思 -->
          <el-input v-model="editUser.password"></el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item label="邮箱" prop="email"> <!-- prop什么意思 -->
          <el-input v-model="editUser.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer"> 
        <el-button @click="deleteDialogShow = false">取消</el-button>
        <el-button type="primary" @click="editUserForm">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 分页请求
      queryInfo: {
        query: "",
        pageNum: 1,
        pageSize: 5,// 因为分页了，所以才返回前5条数据
      },
      // 添加用户
      addUser: {
        username: '',
        password: '',
        email: ''
      },
      // 编辑用户
      editUser: {
        username: '',
        password: '',
        email: ''
      },
      userList: [],
      total: 0,
      dialogShow: false,
      deleteDialogShow: false,
      // 添加用户验证规则
      addUserRules: {
          // 校验用户名
          username: [
              { required: true, message: '用户名为必填项', trigger: 'blur' },// 必填项验证
              { min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'blur' }// 验证长度
          ],
          // 校验密码
          password: [
              { required: true, message: '用户密码为必填项', trigger: 'blur' },// 必填项验证
              { min: 6, max: 10, message: '长度在 6~10 个字符', trigger: 'blur' }// 验证长度
          ],
          // 校验邮箱
          email: [
            {required: true, message: '用户名为必填项', trigger: 'blur'}, //  validator: checkEmail,
            {min: 10,max: 20,message: '长度在 10 到 20 个字符',trigger: 'blur'}
          ]
      },
      // 编辑用户验证规则
      editUserRules: {
          // 校验用户名
          username: [
              { required: true, message: '用户名为必填项', trigger: 'blur' },// 必填项验证
              { min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'blur' }// 验证长度
          ],
          // 校验密码
          password: [
              { required: true, message: '用户密码为必填项', trigger: 'blur' },// 必填项验证
              { min: 6, max: 10, message: '长度在 6~10 个字符', trigger: 'blur' }// 验证长度
          ],
          // 校验邮箱
          email: [
            {required: true, message: '用户名为必填项', trigger: 'blur'}, //  validator: checkEmail,
            {min: 10,max: 20,message: '长度在 10 到 20 个字符',trigger: 'blur'}
          ]
      }
    };
  },
  created() {
    this.getUserList();
  },
  methods: {
    getUserList() {
      this.$http
        .post(
          // 路径前边的斜杠可写可不写，发送参数到后台时
          "/alluser",
          this.queryInfo
        )
        .then((res) => {
          console.log("this.queryInfo：");
          console.log(this.queryInfo);
          let receiver = res.data;
          this.userList = receiver.data;
          this.total = receiver.numbers;
          console.log(receiver);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    handleSizeChange(pageSize) {
      this.queryInfo.pageSize = pageSize;
      this.getUserList();
    },
    handleCurrentChange(pageNum) {
      this.queryInfo.pageNum = pageNum;
      this.getUserList();
    },
    updateState(userinfo) {
      this.$http
        .post(`/updatestate?id=${userinfo.id}&state=${userinfo.state}`)
        .then((res) => {
          const receiver = res.data;
          if (receiver != "success") {
            userinfo.state = !userinfo.state;
            return this.$message.error("操作失败");
          }
          this.$message.success("操作成功");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 验证邮箱
    checkEmail(rule, value, callback){
      // 通过reg快捷键得到验证邮箱的正则表达式(我的vscode好像没有)
      const regEmail = /^\w+@\w+(\.\w+)+$/;
      if (regEmail.test(value)) {
          return callback()
      }
      //返回一个错误提示
      callback(new Error('请输入合法的邮箱'));
    },
    deleteDialogClosed(){
      this.$refs.editUserRef.resetFields();
    },
    getUser(id){
      console.log("要编辑的用户编号：" + id);
      this.$http.post(
        '/getuser?id='+id
      ).then(res=>{
        const receiver = res.data;
        if(receiver.flag!='success'){
          return this.$message.error("操作失败");
        }
        this.editUser.username = receiver.user.username;
        this.editUser.password = receiver.user.password;
        this.editUser.email = receiver.user.email;
      }).catch(error=>{
        console.log(error);
      });
    },
    editUserForm(){
      this.$refs.editUserRef.validate(valid=>{
        if(!valid){
          return this.$message.error("操作失败");
        }
        this.$http.post(
          '/edituser',
          this.editUser
        ).then(res=>{
          const receiver = res.data;
          if(receiver!='success'){
            return this.$message.error("操作失败");
          }
          this.deleteDialogShow = false;
          this.$message.success("更改用户信息成功");
          this.getUserList();
        }).catch(error=>{
          console.log(error);
        });
      });
    },
    dialogClosed(){
      this.$refs.addUserRef.resetFields();
    },
    addUserForm(){
      this.$refs.addUserRef.validate(valid=>{
        if(!valid) return;
        this.$http.post(
                      '/adduser',
                      this.addUser).
                   then(res=>{
                      const receiver = res.data;
                      if(receiver!='success'){
                        this.$message.error("添加用户失败");
                      }
                      this.$message.success("添加用户成功！！");
                      this.dialogShow = false;
                      this.getUserList();
                   }).catch(error=>{
                      console.log(error);
                   });
        console.log("添加用户，重新加载");
      });
    },
    async deleteUser(id){
      const confirmResult = await this.$confirm('此操作将永久删除用户，是否继续操作？','提示',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(error=>error)

      //判断是否要删除
      if(confirmResult!='confirm'){
        return this.$message.info("已经取消删除");
      }

      this.$http.delete(
        '/deleteuser?id='+id
      ).then(res=>{
        const receiver = res.data;
        if(receiver!='success'){
          return this.$message.error("操作失败");
        }
        this.$message.success("删除用户成功！");
        this.getUserList();
      }).catch(error=>{
        console.log(error);
      });
    }
  },
};
</script>

<style lang="less" scoped>
// 导航栏样式
.el-breadcrumb {
  margin-bottom: 15px;
  font-size: 17px;
}
</style>