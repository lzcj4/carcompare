import Vue from 'vue'

//时间戳转换成日期格式（yyyy-MM-dd）
function formatDate(value) {
  if(!value){
    return "";
  }

  var date = new Date(value);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  return year + "-" + month + "-" + day;
}
Vue.filter("formatDate", formatDate);
Vue.prototype.formatDate = formatDate;


//时间戳转换成日期时间格式(yyyy-MM-dd hh:mm:ss)
function formatDateTime(value) {
  if(!value){
    return "";
  }

  var date = new Date(value);
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  var hours = date.getHours();
  var minutes = date.getMinutes();
  var seconds = date.getSeconds();
  if (month < 10) {
    month = "0" + month;
  }
  if (day < 10) {
    day = "0" + day;
  }
  if(hours < 10){
    hours = "0" + hours;
  }
  if(minutes < 10){
    minutes = "0" + minutes;
  }
  if(seconds < 10){
    seconds = "0" + seconds;
  }
  return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}
Vue.filter("formatDateTime", formatDateTime);
Vue.prototype.formatDateTime = formatDateTime;


//对象深拷贝
function deepCopy(obj){
  return JSON.parse(JSON.stringify(obj));
}
Vue.prototype.deepCopy = deepCopy;


Date.prototype.format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}  


Date.prototype.toDateTimeString = function(){
  return this.format("yyyy-MM-dd hh:mm:ss")
}
