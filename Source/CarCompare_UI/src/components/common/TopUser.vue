<template>
<div>
    <el-dropdown trigger="click">
        <span class="el-dropdown-link userinfo-inner" :class="isInManage ? 'userinfo-inner-color-in-manage':'userinfo-inner-color-in-home'"><img :src="user.userface" /> {{user.name}}</span>
        <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-if="showHomeMenu"><router-link to="/home">回到首页</router-link></el-dropdown-item>            
            <el-dropdown-item @click.native="showEditPassword">修改密码</el-dropdown-item>
            <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
    </el-dropdown>  

    <!--修改密码-->
    <edit-password ref="editPassword"></edit-password> 

    <!--用户管理-->   
    <frontend-user-manage ref="frontendUserManage" />
</div>
</template>

<script>
import {logout} from '@/api'
import editPassword from '@/components/common/EditPassword.vue'
import frontendUserManage from '@/components/common/FrontendUserManage.vue'

export default {
    name: 'topUser',
    components:{
        editPassword,
        frontendUserManage
    },
    props:{
        //父组件
        parent:{
            type: String,
            default: function(){
                return "manage";
            }
        },
        //启用返回首页菜单
        enableHomeMenu: {
            type: Boolean,
            default: function () {
                return false;
            }
        },      
        //启用管理中心菜单  
        enableManageMenu: {
            type: Boolean,
            default: function () {
                 return false;
            }
        }
    },
    data(){
        return {
           
        }
    },
    methods:{
        showEditPassword: function(){
            this.$refs.editPassword.show();
        },
        //退出登录
        logout: function () {
            var self = this;
            this.$confirm('注销登录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                logout().then(res=>{
                    if(res.success){                        
                        self.$store.commit('logoutAndRedirect');
                    }
                });
            }).catch(() => {
            });
        } 
    },
    computed: {
        user(){
            return this.$store.state.user
        },
        showHomeMenu(){
            return false;
        },        
        isInManage(){            
            return this.parent != 'home'
        }
    }    
}
</script>
<style lang="scss" scoped>
a {
    text-decoration: none;
} 

.router-link-active {
    text-decoration: none;
}  

.userinfo-inner {
    cursor: pointer;       
    img {
        width: 40px;
        height: 40px;
        border-radius: 20px;
        margin: 10px 0px 10px 10px;
        float: right;
    }
}

.userinfo-inner-color-in-manage{
    color:#fff;
}

.userinfo-inner-color-in-home{
    color:#000;
}
</style>


