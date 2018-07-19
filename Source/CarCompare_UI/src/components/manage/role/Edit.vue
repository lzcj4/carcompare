<template>
    <icare-dialog :title="title" :visible="visible" @cancel="onCancel" @submit="onSubmit">
      <el-form slot="content" :model="formModel" :rules="rules" ref="formModel" label-width="100px" v-loading="isLoading">
          <el-form-item label="名称:" prop="name">
              <el-input prefix-icon="el-icon-edit" v-model="formModel.name" placeholder="请输入名称" maxlength="18" auto-complete="off"></el-input>
          </el-form-item>          
          <el-form-item label="编码:" prop="code">
              <el-input prefix-icon="el-icon-edit" v-model="formModel.code" placeholder="请输入编码"  maxlength="18"auto-complete="off" :disabled="codeDisabled"></el-input>
          </el-form-item>          
          <el-form-item label="描述:" prop="description">
              <el-input  type="textarea" prefix-icon="el-icon-edit" v-model="formModel.description" placeholder="请输入描述" maxlength="255"></el-input>
          </el-form-item>  
          <el-form-item label="默认页面:" prop="defaultUrl" v-show="defaultUrlVisible">
            <el-select v-model="formModel.defaultUrl" placeholder="请选择">
                <el-option value=""></el-option>
                <el-option
                v-for="item in menus"
                :key="item.id"
                :label="item.name"
                :value="item.path">
                <span style="float: left">{{ item.name }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.path }}</span>
                </el-option>
            </el-select>
          </el-form-item>             
          <el-form-item label="后端角色:" prop="isBackend">
              <el-switch v-model="formModel.isBackend"></el-switch>
          </el-form-item>                      
          <!-- <el-form-item label="是否启用:" prop="enabled">
              <el-switch v-model="formModel.enabled"></el-switch>
          </el-form-item>                        -->
      </el-form>
    </icare-dialog>  
</template>

<script>
import {addRole, updateRole, getLeafMenus} from '@/api'
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
            title: "新增角色",  
            formModel: {
                code: "",
                name: "",
                description: "",
                defaultUrl: "",
                isBackend: false,
                enabled: true
            },
            rules: {
                code: [
                    {required: true, message: '必填:编码', trigger: 'blur'},
                    {min: 3, max: 18, message: '长度在 3 到 18 个字符', trigger: 'blur'},
                    {validator: this.$validators.blankValidator('编码'), trigger:'blur'}
                    ],
                name: [
                    {required: true, message: '必填:名称', trigger: 'blur'},
                    {min: 3, max: 18, message: '长度在 3 到 18 个字符', trigger: 'blur'},
                    {validator: this.$validators.blankValidator('名称'), trigger:'blur'}
                    ],
                defaultUrl: [
                    {required: false, message: '必选:默认页面', trigger: 'blur'}
                    ]
            },             
            isLoading: false,
            menus: []
        }
    },
    mounted(){
        let self = this;

        this.$nextTick(function(){
            getLeafMenus({}).then(res=>{
                self.menus = res.data;
            });
        });
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
                return false;
            }

            if (this.formModel.id) { //更新              
                this.isLoading = true;                         
                updateRole({ roleId: this.formModel.id, roleInfo: this.formModel }).then(res=> {
                    if (res.success) {
                        var data = res.data
                        _this.$message({type: "success", message: "操作成功"})
                        _this.doEditCompleted()
                    }
                    _this.isLoading = false;
                })
            } else { //添加
                _this.isLoading = true;
                addRole({roleInfo: _this.formModel}).then(res=> {
                    if (res.success) {
                        var data = res.data;
                        _this.$message({type: "success", message: "操作成功"});
                        _this.doEditCompleted()
                    }
                    _this.isLoading = false;                    
                })
                }          
            });
        },
        doEditCompleted(){
            this.visible = false;
            this.clearForm();
            this.$emit("editCompleted")
        },
        clearForm(){
            this.formModel = {
                code: "",
                name: "",
                description: "",
                defaultUrl: "",
                isBackend: false,
                enabled: true
            }
            this.$refs['formModel'].resetFields();
        }            
    },    
    computed: {
        codeDisabled:function(){
            return this.propsEditMode == this.GLOBAL.formAction.EDIT;
        },
        defaultUrlVisible() {                       
　　　　     return this.formModel.isBackend;
　　    }
    },
    watch: {
        propsEditInfo(value) {
            this.formModel = value
        },
        propsVisible(value){
            this.visible = value
        },
        propsEditMode(value){
            this.title = value == this.GLOBAL.formAction.ADD ? "新增角色" : "编辑角色"
        },
        defaultUrlVisible(value){
            this.rules.defaultUrl[0].required = value;
            if(!value){
                this.formModel.defaultUrl = "";
            }
        } 
    }
}
</script>


