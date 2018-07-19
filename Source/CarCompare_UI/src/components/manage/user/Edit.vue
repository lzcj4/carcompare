<template>
    <icare-dialog :title="title" :visible="visible" @cancel="onCancel" @submit="onSubmit">
      <el-form slot="content" :model="formModel" :rules="rules" ref="formModel" label-width="100px">
          <el-form-item label="姓名:" prop="name">
              <el-input prefix-icon="el-icon-edit" v-model="formModel.name" placeholder="请输入姓名" maxlength="18" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="用户名:" prop="username">
              <el-input prefix-icon="el-icon-edit" v-model="formModel.username" placeholder="请输入用户名" maxlength="18" :disabled="usernameDisabled"></el-input>
          </el-form-item>                      
      </el-form>
    </icare-dialog>
</template>

<script>
import {addUser, updateUser} from '@/api'
import icareDialog from '@/components/base/Dialog'

export default {
    name: 'edit',
    components:{
        icareDialog
    },
    props: ['propsVisible', 'propsEditInfo', 'propsEditMode'],
    data(){
        let self = this;
       
        return {
            visible: false,
            title: "新增用户",
            isLoading: false,
            isDirty: false,
            rules: {
                name: [
                    {required: true, message: '必填:姓名', trigger: 'blur'},
                    {min: 3, max: 18, message: '长度在 3 到 18 个字符', trigger: 'blur'},
                    {validator: this.$validators.blankValidator('姓名'), trigger:'blur'}
                    ],
                username: [
                    {required: true, message: '必填:用户名', trigger: 'blur'},
                    {min: 3, max: 18, message: '长度在 3 到 18 个字符', trigger: 'blur'},
                    {validator: this.$validators.blankValidator('用户名'), trigger:'blur'}
                    ]
            },        
            formModel: {}
        }
    },
    methods: {
        onCancel(){
            this.visible = false;
            this.clearForm();
            this.$emit("editClosed");
        },        
        onSubmit(){
            var _this = this
            this.$refs['formModel'].validate((valid) => {
                if (!valid) {
                    return false
                }

                if (_this.formModel.id) { //更新
                    _this.isLoading = true
                    updateUser({ userId: _this.formModel.id, userInfo: _this.formModel }).then(res=> {
                        if (res.success) {
                            var data = res.data
                            _this.$message({type: "success", message: "操作成功"})
                            _this.onEditCompleted();
                        }
                        _this.isLoading = false;                    
                    })
                } else { //添加
                    _this.isLoading = true;
                    addUser({ userInfo: _this.formModel }).then(res=> {
                        if (res.success) {
                            var data = res.data;
                            _this.$message({type: "success", message: "操作成功"});
                            _this.onEditCompleted();
                        }
                        _this.isLoading = false;                    
                    })
                }          
            })                        
        },  
        onEditCompleted(){
            this.visible = false;
            this.clearForm();
            this.$emit("editCompleted");
        },     
        clearForm(){
            this.$refs['formModel'].resetFields();
        }       
    }, 
    computed: {
        usernameDisabled:function(){
            return this.propsEditMode == this.GLOBAL.formAction.EDIT;
        }
    },
    watch: {
        propsEditInfo(value) {
            this.formModel = value;
        },
        propsVisible(value){
            this.visible = value
        },
        propsEditMode(value){
            this.title = value == this.GLOBAL.formAction.ADD ? "新增用户" : "编辑用户"
        }
    }
}
</script>

