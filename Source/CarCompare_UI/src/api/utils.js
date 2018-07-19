import axios from 'axios'
import {Message} from 'element-ui'
import store from '../store'

axios.interceptors.request.use(config=> {
  //认证以后，需要在请求头中设置token
  if (store.state.user && store.state.user.token) { 
    config.headers.Authorization = 'Bearer ' + store.state.user.token
    console.log("token:" + store.state.user.token);
  }

  return config;
}, err=> {
  Message.error({message: '请求超时!'});
  return Promise.resolve(err);
})

let toLogin = function(){
  setTimeout(() => {
    store.commit('logoutAndRedirect');
  }, 2000);  
}

const MESSAGE_DURATION = 3000;

axios.interceptors.response.use(
  response=> {
    if(response.headers.newtoken){
      store.commit('updateToken', response.headers.newtoken);
    }    

    if (!response.data.success) {
      Message.error({dangerouslyUseHTMLString: true, message: response.data.error, duration: MESSAGE_DURATION});
    }  

    return response.data;
  }, 
  err=> {     
    if(err.response == undefined){
      Message.error({message: '访问服务器失败!'});
      console.error(err.message);
    }else{
      if (err.response.status == 401 || err.response.status == 403) {  
        Message.warning({message: err.response.data.error, duration: MESSAGE_DURATION});
        toLogin();
      }else if (err.response.status == 404||err.response.status == 504) {
        Message.warning({message: '请求的资源不存在或访问失败!'});
      }else {
        console.error("未知错误[" + err.response.status + "]")
        Message.error({message: '未知错误!'});
      }    
    }
    
    return Promise.resolve(err.response.data);
})

let base = '/api';

export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
}

export const uploadFileRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
}

export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
}

export const deleteRequest = (url) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`
  });
}

export const getRequest = (url) => {  
  console.info("getRequest:" + url)
  return axios({
    method: 'get',
    url: `${base}${url}`
  });
}

export const all = (requests, callback) =>{
  return axios.all(requests).then(axios.spread(callback));
}