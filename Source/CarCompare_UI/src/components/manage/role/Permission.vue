<template>
<div class="role-permission-container">
     <icare-dialog :title="title" :visible="visible" @cancel="onCancel" @submit="onSubmit">
        <el-form slot="content">
            <el-tree :props="permissionForm.props"
                    :data="permissionForm.menuTree"
                    :default-checked-keys="permissionForm.hasMenuIds"
                    default-expand-all
                    node-key="id"
                    ref="menuTree"
                    show-checkbox
                    highlight-current                
                    v-loading="loading">
            </el-tree>            
        </el-form>
    </icare-dialog>  
</div>
   
</template>


<script>
import { getMenuTree, updateRolePermission } from "@/api";
import icareDialog from "@/components/base/Dialog";

export default {
  name: "permission",
  components: {
    icareDialog
  },
  props: ["propsVisible", "propsRole"],
  data() {
    return {
      visible: false,
      title: "角色权限",
      permissionForm: {
        props: {
          label: "name",
          children: "children"
        },
        menuTree: [],
        hasMenuIds: []
      },
      loading: false,
      expandAll: false
    };
  },
  mounted: function() {},
  methods: {
    loadMenus() {
      var self = this;
      this.loading = true;
      getMenuTree({ roleId: this.propsRole.id }).then(res => {
        if (res.success) {
          var data = res.data;
          self.permissionForm.menuTree = data.menus;
          self.permissionForm.hasMenuIds = data.hasMenuIds;
          self.expandAll = true;
        }
        this.loading = false;
      });
    },
    onCancel() {
      this.visible = false;
      this.clearForm();
      this.$emit("permissionFormClosed");
    },
    onSubmit() {
      var checkedMenuIds = this.$refs["menuTree"].getCheckedKeys(true);
      var self = this;
      updateRolePermission({
        roleId: this.propsRole.id,
        menuIds: checkedMenuIds
      }).then(res => {
        if (res.success) {
          self.$message({ type: "success", message: "操作成功" });
          self.clearForm();
          self.$emit("permissionFormCompleted");
        }
      });
    },
    clearForm() {
      this.propsRole = null;
    }
  },
  watch: {
    propsVisible(value) {
      this.visible = value;
    },
    propsRole(value) {
      if(value == null){
        return;
      }
      this.loadMenus();
    }
  }
};
</script>

<style lang="scss">
.role-permission-container {
  .el-tree {
    width: 100%;
    height: 350px;
    overflow-y: scroll;
  }
  .el-tree > .el-tree-node {
    display: inline-block !important;
  }
}
</style>

