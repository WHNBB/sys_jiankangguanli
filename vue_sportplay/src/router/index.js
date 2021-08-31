import Vue from 'vue'
import VueRouter from 'vue-router'

// 引入Login组件
import Login from '../components/Login.vue' 
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import UserList from '../components/admin/UserList.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    // redirect 用另一个路径覆盖原来路径
    redirect: "/login"
  },{
    path: "/login",
    component: Login,
    name:"ceshi1",
    meta:{
      zidingyi: "ceshi"
    }
  },
  {
    path: "/home",
    component: Home,
    redirect: '/welcome',
    children:[
      {path: "/welcome",component: Welcome},
      {path: "/user",component: UserList},
    ]
  },
]

// 创造router对象对路由组件进行管理
const router = new VueRouter({
  routes // routes: routes 的简写
})

// 设置路由守卫，只有登录后才能访问除 登录页面 的其他页面
// 跳转登录页面时，不拦截
router.beforeEach((to,from,next)=>{
  if(to.name=="ceshi1"){
    console.log("自定义函数生效");
    return next();
  } 
  // 获取存储的user，判断是否登录
  const userFlag = window.sessionStorage.getItem("user");
  if(!userFlag) return next('/login');
  next();
})

export default router
