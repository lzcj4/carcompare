<template>
    <el-dialog :title="title" :visible.sync="visible" width="800px" height="500px" :close-on-click-modal="false">
        <div style="height:500px">
            <user-list :propPageSize="this.GLOBAL.settings.frontend.userListPageSize" />
        </div>
    </el-dialog>
</template>

<script>
import icareDialog from '@/components/base/Dialog.vue';
import userList from '@/components/manage/user/List.vue'

export default {    
    components:{
        icareDialog,
        userList
    },
    data(){
        return {
            title: "用户管理",
            visible: false                
        }
    },
    methods: {
        show(){
            this.visible = true;
        },
        hide(){
            this.visible = false;
        }       
    }
}
</script>
<style lang="scss">
	@import '../../assets/styles/backend-common.scss';
</style>