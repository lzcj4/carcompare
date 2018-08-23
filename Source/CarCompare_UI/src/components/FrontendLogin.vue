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
                    <h3 class="login_title">车型网登录</h3>
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
                    <el-form-item prop="captcha">
                    <el-input type="text" v-model="loginForm.captcha" placeholder="请输入验证码" auto-complete="off" :maxlength="8" @keyup.enter.native="doLogin" v-if="useCaptcha">
                        <el-button slot="append" @click.native.prevent="verifyClick" v-loading="captchaLoading">
                          <img id="captchaImage" alt="请点击刷新" height="30" v-show="captchaImageVisible" style="margin-top:5px;">
                          <span v-show="!captchaImageVisible">点击显示验证码</span>
                        </el-button>
                    </el-input>
                    </el-form-item>                  
                    <el-form-item style="width: 100%">
                    <el-button type="primary" @click.native.prevent="doLogin" style="width: 100%">登录</el-button>
                    </el-form-item>
                </el-form>   

            </div>            
        </div>
    </div>

    <common-footer></common-footer>  
</div>
</template>
<script>
import { mapState, mapActions } from 'vuex'
import { getCaptchaImage } from "@/api";
import commonFooter from "@/components/common/CommonFooter.vue"

export default {
  components: {
    commonFooter
  },    
  data() {
    return {
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        captcha: [{ required: this.GLOBAL.settings.captchaEnable, message: "请输入验证码", trigger: "blur" }]
      },
      checked: true,
      loginForm: {
        username: "",
        password: "",
        captcha: ""
      },
      loading: false,
      captchaImageVisible: false,
      captchaLoading: false    
    };
  },

  methods: {
    verifyClick: function() {      
      var _this = this;
      _this.captchaLoading = true;
      getCaptchaImage().then(res => {
        if (res.success) {        
          document.getElementById("captchaImage").src='data:image/jpeg;base64,'+res.data.img;
          _this.captchaImageVisible = true;
          _this.captchaLoading = false;
        }
      });
    },
    ...mapActions({
        toFrontend: 'toFrontend'
    }),
    doLogin: function() {
      var _this = this;
      _this.loading = true; 
      
      _this.$refs.loginForm.validate((valid)=>{        
        if(!valid){
          _this.loading = false;          
          return false         
        }

        _this.toFrontend({
          username: _this.loginForm.username,
          password: _this.loginForm.password,
          captcha: _this.loginForm.captcha
        }).then(res => {
          _this.loading = false;
          if (res.success) {
            var path = _this.$route.query.redirect;
            _this.$router.replace({
              path: path == "/" || path == undefined ? _this.GLOBAL.settings.frontend.homePage : path
            });
          }else{          
            _this.loading = false;
          }
        }).catch(function (error) {
          _this.loading = false;
        });        
      });
            
    }  
  },

  mounted () {
    if(this.GLOBAL.settings.captchaEnable){
      //页面打开时，默认显示验证码
      this.verifyClick();
    }
  },

  computed: {
    useCaptcha(){      
      return this.GLOBAL.settings.captchaEnable
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
    .el-button{
      padding:7px 15px;
    }    
  }
}
</style>