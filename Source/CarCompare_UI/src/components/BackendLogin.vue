<template>
<div class="login-container">
    <div class="login-main">
        <div class="logo"><img src="../assets/images/login/login_logo.png"></div>
        <div class="content">
            <div class="left">
                <img src="../assets/images/login/login_left.png" width="700">
            </div>
            <div class="right">

                <el-form ref="loginForm" :model="loginForm" :rules="rules" class="login-form" label-position="left" label-width="0px" v-loading="loading">
                    <h3 class="login_title">车型网控制台登录</h3>
                    <div class="line">
                        <div class="line-grey"></div>
                        <div class="line-blue"></div>
                        <div class="line-grey"></div>
                    </div>
                    <el-form-item prop="username">
                    <el-input type="text" v-model="loginForm.username" placeholder="账号" auto-complete="off" :maxlength="30">
                        <template slot="prepend"><img src="../assets/images/login/login_user.png"></template>
                    </el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                    <el-input type="password" v-model="loginForm.password" placeholder="密码" auto-complete="off" :maxlength="30" @keyup.enter.native="doLogin">
                        <template slot="prepend"><img src="../assets/images/login/login_password.png"></template>
                    </el-input>
                    </el-form-item>
                    <el-form-item >
                    </el-form-item>                  
                    <el-form-item style="width: 100%">
                    <el-button type="primary" @click.native.prevent="doLogin" style="width: 100%">登录</el-button>
                    </el-form-item>
                </el-form>   

            </div>            
        </div>
    </div>

    <div class="login-footer">
        <copyright></copyright>
    </div>
</div>
</template>
<script>
import { mapState, mapActions } from 'vuex'
import copyright from "@/components/common/Copyright.vue"

export default {
  components: {
    copyright
  },  
  data() {
    return {
      rules: {
        username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
      checked: true,
      loginForm: {
        username: "",
        password: ""
      },
      loading: false
    };
  },
  methods: {
    ...mapActions({
        toBackend: 'toBackend'
    }),    
    doLogin: function() {
      var _this = this;
      _this.loading = true;     

      _this.$refs.loginForm.validate((valid)=>{        
        if(!valid){
          _this.loading = false;
          return false
        }

        _this.toBackend({
          username: _this.loginForm.username,
          password: _this.loginForm.password
        }).then(res => {          
          _this.loading = false;
          if (res.success) {                
            let redirectPath = _this.$route.query.redirect;
            let defaultPath = res.data.defaultUrl || _this.GLOBAL.settings.backend.homePage;
            
            _this.$router.replace({
              path: redirectPath == "/" || redirectPath == undefined ? defaultPath : redirectPath
            });
          }else{          
            _this.loading = false;
          }
        }).catch(function (error) {
          _this.loading = false;
        });        
      });

    }
  }
};
</script>
<style scoped lang="scss">
@import "./Login.scss";
</style>
<style lang="scss">
.login-container {
  .el-input {
    input {
      background: #f7f9fa !important;
    }
  }
}
</style>
