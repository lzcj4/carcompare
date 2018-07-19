import {Message} from 'element-ui'

export const isNotNullORBlank = (...args)=> {
  for (var i = 0; i < args.length; i++) {
    var argument = args[i];
    if (argument == null || argument == '' || argument == undefined) {
      Message.warning({message: '数据不能为空!'})
      return false;
    }
  }
  return true;
}

/**
 * 是否Chrome浏览器
 */
export const isChrome = ()=>{
  var userAgent = navigator.userAgent;
  return userAgent.indexOf("Chrome") > -1;  
}
