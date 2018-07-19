<template>
<div class="role-set-container">
    <icare-dialog :title="title" :visible="visible" width="565px" @cancel="onCancel" @submit="onSubmit">
        <el-form slot="content">
            <el-radio-group v-model="editForm.checkedRoles[0]" v-loading="loading">
                <el-radio v-for="role in editForm.roles" :label="role.id" :key="role.name" size="mini" :border="true">{{role.name}}</el-radio>
            </el-radio-group>            
        </el-form>
    </icare-dialog>  
</div>
</template>
<style>
.role-set-container .el-radio-group{
    line-height: 80px;
}
</style>

<script>
import {getUserRoles, updateUserRoles} from '@/api'
import icareDialog from '@/components/base/Dialog'

export default {
    name: 'role',
    components:{
        icareDialog
    },    
    props: ['propsVisible', 'propsUser'],
    data(){
        return {
            visible: false,
            title: "用户角色",  
            loading: false,     
            editForm: {               
                roles: [],
                checkedRoles: []
            },
            expandAll: false
        }
    },
    mounted: function () {
    },    
    methods: {
        loadData(){
            var _this = this;
            this.loading = true;
            getUserRoles({userId: this.propsUser.id}).then(res=> {
                if (res.success) {
                    var data = res.data;
                    _this.editForm.roles = data.roles;
                    _this.editForm.checkedRoles = data.hasRoleIds;
                    _this.expandAll = true;
                }
                this.loading = false;
            })
        },
        onCancel(){
            this.visible = false;
            this.clearForm();
            this.$emit("roleFormClosed");
        },        
        onSubmit(){
            var _this = this;
            updateUserRoles({
                userId: this.propsUser.id,
                roleIds: this.editForm.checkedRoles
            }).then(res=> {
                if (res.success) {
                    _this.$message({type: "success", message: "操作成功"});
                    _this.visible = false;
                    _this.clearForm();
                    _this.$emit("roleFormCompleted")                    
                }
            })
        },    
        clearForm(){
            this.propsUser = null;
        }             
    },    
    watch: {
        propsVisible(value){
            this.visible = value;
        },
        propsUser(value){
            if(value == null){
                return;
            }
            this.loadData(); 
        }
    }
}
</script>

