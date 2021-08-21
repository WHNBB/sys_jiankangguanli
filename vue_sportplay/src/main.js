// main.js中引入全局要用到的资源
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
// 添加全局样式
import './assets/css/global.css'
// 引入iconfont图标
import './assets/font/iconfont.css'
// 引入 axios
import axios from 'axios'
import VueRouter from 'vue-router'

// 设置访问根路径
axios.defaults.baseURL = "http://localhost:9000"

// 将axios挂载到Vue原型，通过this.$http使用axios，'$'符号后边的名字可以任意起，不用与挂载的包名相同
Vue.prototype.$http = axios

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
