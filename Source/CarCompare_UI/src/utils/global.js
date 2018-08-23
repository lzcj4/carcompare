//设置参数
const settings = {
    //通用参数
    common: {
        pageSize: 15,              
        //显示无结果字符  
        emptyText: "检索到0个结果",
        copyright: "版权所有&copy; 浙江捷尚视觉科技股份有限公司",
        picStatement: "图片均来源于网络，若有疑问请联系：0571-26292569"
    },
    //前台参数
    frontend: {
        //车型搜索结果列表每页记录数
        carListPageSize: 30,
        //用户管理列表每页记录数
        userListPageSize: 7, 
        //前端登录后默认页面
        homePage: '/home',
    },
    //后端参数
    backend:{
        //车型列表每页记录数
        carListPageSize: 30,
        //后端登录后默认页面
        homePage: '/manage'
    }
}

//表单操作枚举
const formAction = {
    //新增操作
    ADD: "ADD",

    //编辑操作
    EDIT: "EDIT"    
}

//登录模式枚举
const loginMode = {
    //登录前台
    FRONTEND: "FRONTEND",

    //登录后台
    BACKEND: "BACKEND"
}

//车型审核状态
const vehicleAuditStatus = {
    //未审核
    UNAUDITED: 0,

    //已审核
    AUDITED: 1
}

export default{
    formAction,
    loginMode,
    vehicleAuditStatus,
    settings
}