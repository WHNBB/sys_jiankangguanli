// main.js中引入全局要用到的资源
import Vue from 'vue'
import App from './App.vue'
// 
import router from './router'

import './plugins/element.js'
// 添加全局样式
import './assets/css/global.css'
// 引入iconfont图标
import './assets/font/iconfont.css'
import VueRouter from 'vue-router'
// 引入 axios
import axios from 'axios'
// 将axios挂载到Vue原型，通过this.$http使用axios，'$'符号后边的名字可以任意起，不用与挂载的包名相同
Vue.prototype.$http = axios

// 导入包之后就可以使用了，因为导入了axios，所以可以使用axios的方法
// main.js配置信息在全局都有效
// 设置访问根路径
axios.defaults.baseURL = "http://localhost:9000"

Vue.config.productionTip = false

// router 实例注入到 vue 根实例中,就可以使用路由了
new Vue({
  router,
  // App在main.js的最上方被导入
  render: h => h(App)
}).$mount('#app')
