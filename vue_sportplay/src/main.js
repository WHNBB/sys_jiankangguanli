// main.js中引入全局要用到的资源
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
// 添加全局样式
import './assets/css/global.css'
// 引入iconfont图标
import './assets/font/iconfont.css'
// 引入axios
import axios from 'axios'
import VueRouter from 'vue-router'
// 挂载axios到vue
Vue.prototype.$http = axios
// 设置访问根路径
axios.defaults.baseURL = "http://localhost:9000"

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
