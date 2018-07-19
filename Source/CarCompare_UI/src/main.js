// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router'
import store from './store'
import './utils/filters'
import './utils/directives'
import global from './utils/global'
import { requestAll, getSettings, getLicenseValidationResult } from '@/api'
import 'font-awesome/css/font-awesome.min.css'
import './assets/styles/message-box.scss'
import './assets/styles/dialog.scss'
import './assets/icon/iconfont.css'
import $ from 'jquery'
import {Message, MessageBox} from 'element-ui'
import VueLazyload from 'vue-lazyload'
import Validators from "./lib/validators"  

Vue.use(Validators)
Vue.use(ElementUI)
Vue.use(VueLazyload, {  
  preLoad: 1.3,  
  error: 'assets/images/defaultcar.jpg',  
  loading: 'assets/images/defaultcar.jpg',  
  attempt: 1  
})
Vue.config.productionTip = false
Vue.prototype.GLOBAL = global
Vue.prototype.GLOBAL.settings = Vue.prototype.GLOBAL.settings || {}

requestAll([getSettings({}), getLicenseValidationResult({})], function(res1, res2){
  store.commit('updateLicenseValidationResult', res2.data)

  Vue.prototype.GLOBAL.settings = Object.assign(Vue.prototype.GLOBAL.settings, res1.data)

  new Vue({
    el: '#app',
    router,
    store,
    template: '<App/>',
    components: {App}
  })
});