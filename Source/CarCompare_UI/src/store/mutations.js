import userface from '@/assets/images/userface.png'

const doClear = function(state){
  window.localStorage.removeItem('user');
  state.user = {};
  state.routes = null;
}

const doSaveUser = function(state){
  window.localStorage.setItem('user', JSON.stringify(state.user));
}

export default {
  /**
   * 初始化后台管理菜单
   * @param {*} state 
   * @param {*} menus 
   */
  initMenu(state, menus){
    state.routes = menus;
  },

  /**
   * 设置用户登录状态
   * @param {d} state 
   * @param {*} user 
   */
  login(state, user){      
    state.user = user;
    state.user.userface = userface;
    doSaveUser(state);
    state.routes = null;
  },
  
  /**
   * 清除用户登录状态
   * @param {*} state 
   */
  logout(state){
    doClear(state);
  },

  /**
   * 更新token
   * @param {*} state 
   * @param {*} newToken 
   */
  updateToken(state, newToken){
    state.user.token = newToken;
    doSaveUser(state);
    console.log("newtoken:" + state.user.token);
  },

  /**
   * 退出并重定向到登录界面
   * @param {*} state 
   */
  logoutAndRedirect(state){    
    //根据用户登录模式，来决定重定向到后端登录界面或前端登录界面           
    let path = state.user.loginMode == this._vm.GLOBAL.loginMode.BACKEND ? "/admin" : "/";
    doClear(state);
    window.location.href = path;      
  },

  /**
   * 更新许可证验证结果
   * @param {*} state 
   * @param {*} licenseValidationResult 
   */
  updateLicenseValidationResult(state, licenseValidationResult){
    state.licenseValidationResult = licenseValidationResult;
  }
}