<template>
    <icare-dialog :title="title" :visible="visible" @cancel="onCancel" @submit="onSubmit">
      <el-form slot="content" :model="formModel" :rules="rules" ref="formModel" label-width="100px" v-loading="isLoading">
          <el-form-item label="原始密码:" prop="originalPassword">
              <el-input prefix-icon="el-icon-edit" type="password" :maxlength="50" v-model="formModel.originalPassword" placeholder="请输入原始密码" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="新密码:" prop="newPassword">
              <el-input prefix-icon="el-icon-edit" type="password" :maxlength="50" v-model="formModel.newPassword" placeholder="请输入新密码" auto-complete="off"></el-input>
          </el-form-item>          
          <el-form-item label="确认密码:" prop="verifyPassword">
              <el-input prefix-icon="el-icon-edit" type="password" :maxlength="50" v-model="formModel.verifyPassword" placeholder="请输入确认密码" auto-complete="off"></el-input>
          </el-form-item>                     
      </el-form>
    </icare-dialog>  
</template>

<script>
import {resetPassword} from '@/api'
import icareDialog from '@/components/base/Dialog.vue';

export default {
    name: 'editPassword',
    components:{
        icareDialog
    },
    data(){
        return {
            title: "修改密码",
            visible: false,            
            rules: {
                originalPassword: [{required: true, message: '必填:原始密码', trigger: 'blur'}],
                newPassword: [{required: true, message: '必填:新密码', trigger: 'blur'}],
                verifyPassword: [{required: true, message: '必填:确认密码', trigger: 'blur'}]
            },        
            formModel: {
                originalPassword: "",
                newPassword: "",
                verifyPassword: ""
            },
            isLoading: false          
        }
    },
    methods: {
        show(){
            this.visible = true;
        },
        onCancel(){            
            this.visible = false;
            this.clearForm();
        },   
        onSubmit(){
            var self = this
            this.$refs['formModel'].validate((valid) => {
                if (!valid) {
                    return false;
                }

                if(self.formModel.newPassword.trim().length == 0){
                    self.$message({type: "error", message: "必填:新密码"})
                    return false;
                }

                if(self.formModel.newPassword != self.formModel.verifyPassword){
                    self.$message({type: "error", message: "新密码和确认密码不一致"})
                    return false;
                }

                self.isLoading = true;              
                resetPassword(self.formModel).then(res=> {
                    if (res.success) {                        
                        self.visible = false;
                        self.clearForm();
                        self.$message({type: "success", message: "操作成功"})
                    }
                    self.isLoading = false;
                })
            })
        },                
        clearForm(){
            setTimeout(function(){
                this.formModel = {
                    originalPassword: "",
                    newPassword: "",
                    verifyPassword: ""
                };
                this.$refs['formModel'].resetFields();
            }.bind(this), 300);
        }            
    }
}
</script>