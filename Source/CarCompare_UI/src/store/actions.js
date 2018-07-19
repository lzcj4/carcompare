import '../lib/sockjs'
import '../lib/stomp'
import { Message, MessageBox } from 'element-ui'
import { loginToFrontend, loginToBackend, getLicenseValidationResult } from "@/api";

export default {
    /**
     * 登录到前端
     * @param {*} param0 
     * @param {*} loginInfo 
     */
    toFrontend({ commit, rootState }, loginInfo) {
        return new Promise((resolve, reject) => {

            loginToFrontend(loginInfo).then(res => {
                if (res.success) {
                  commit("login", res.data);
                  resolve(res)
                }else{
                  reject(res.error)
                }
            }).catch(err => {
                reject(err.data.message)
            });    

        });
    },    

    /**
     * 登录到后端
     * @param {*} param0 
     * @param {*} loginInfo 
     */
    toBackend({ commit, rootState }, loginInfo) {
        return new Promise((resolve, reject) => {

            loginToBackend(loginInfo).then(res => {
                if (res.success) {
                    commit("login", res.data);
                    resolve(res)
                }else{
                    reject(res.error)
                }
            }).catch(err => {
                reject(err.data.message)
            });   
                     
        });
    },

    /**
     * 连接websocket
     * @param {*} context 
     */
    connect(context){
        let token = context.state.user.token;
        context.state.stomp = Stomp.over(new SockJS("/ws/server"));
        context.state.stomp.connect({}, frame=> {        
            context.state.stomp.subscribe('/user/' + token + '/kickout', function(message){                
                console.info("收到消息：" + message.body);
                MessageBox.alert(message.body, '提示', {
                    type: 'warning',
                    confirmButtonText: '确定',
                    callback: action => {
                        context.commit('logoutAndRedirect');
                    }
                  });                
            });           
        }, failedMsg=> {
            console.warn("websocket连接异常或断开");
        });
    }
}