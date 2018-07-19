import global from '../utils/global'

export default{
    /**
     * 是否登录
     */
    isLoggedIn: function(state){          
        if(!state.user.token){
            return false;
        }

        if(state.user.token == ''){
            return false;
        }

        return true;
    },

    /**
     * 是否前端管理员
     */
    isFrontendAdministrator: function(state){
        if(!state.user){
            return false;
        }

        return state.user.roleCode == global.settings.frontendAdministratorRoleCode;
    }
}