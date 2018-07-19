import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'
import {getSystemMenus} from '@/api'
import FrontendLogin from '@/components/FrontendLogin';
import BackendLogin from '@/components/BackendLogin';
import Home from '@/components/Home';
import Manage from '@/components/Manage';
import whiteList from './directAccess'
import {Message} from 'element-ui'

Vue.use(VueRouter)

//格式化路由
const formatRoutes = (routes)=> {
  let fmRoutes = [];
  routes.forEach(router=> {
    let {
      path,
      component,
      name,
      requireAuth,
      icon,
      children
    } = router;
    
    if (children && children instanceof Array) {
      children = formatRoutes(children);
    }

    let fmRouter = {
      path: path,
      component(resolve){
        require(['@/' + component + '.vue'], resolve);
      },
      name: name,
      icon: icon,
      requireAuth: requireAuth,
      children: children
    };
    fmRoutes.push(fmRouter);
  })
  return fmRoutes;
}

// 默认路由表
let routes = [
  {
    path: '/',
    name: 'loginFrontend',
    component: FrontendLogin,
    hidden: true
  },
  {
    path: '/admin',
    name: 'loginBackend',
    component: BackendLogin,
    hidden: true
  },
  {
    path: '/home',
    name: '首页',
    component: Home,
    hidden: true,
    requireAuth: true    
  }
];

let router = new VueRouter({
  mode: 'history',  
  routes
})

router.beforeEach((to, from, next)=> {       
    let validationResult = store.state.licenseValidationResult;
    if(typeof validationResult.valid != 'undefined' && !validationResult.valid){
      Message.warning({message: validationResult.message + "，请根据此机器码申请许可证：" + validationResult.serverInfo.machineCode, duration: 0});
      return;
    }

    if(to.name == "loginFrontend" || to.name == "loginBackend"){
      next();  
      router.addRoutes([])    
      return;
    }

    if (store.getters.isLoggedIn) {
      if (store.state.routes == null) {        

        getSystemMenus().then(res=> {
          if (res.success) {                                
              var fmtRoutes = formatRoutes(res.data);
              router.addRoutes(fmtRoutes);

              store.commit('initMenu', res.data);
              store.dispatch('connect');              

              next(to.path)
          }
        })        

      }else{    
        if(to.matched.length>0){
          next()
        }else{       
          console.warn('访问页面出错[name:'+to.name+', path:'+to.path+']')   
          Message.warning({message: '您没有访问该页面的权限!'})
          return
        }
      }

    } else {

      // 如果是免登陆的页面则直接进入，否则跳转到登录页面
      if (whiteList.indexOf(to.path) >= 0) {
        console.log('该页面无需登录即可访问')
        next()
      } else {
        console.log('请重新登录')
        router.replace('/')
      }      
        
    }
  }
)

export default router;
