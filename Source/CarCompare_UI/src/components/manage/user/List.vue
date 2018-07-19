<template>
  <div>

    <icare-table>      
      <!--工具条-->
      <el-form slot="search-slot" :inline="true" :model="filters" onsubmit="return false;">
        <el-form-item>
          <el-input clearable prefix-icon="el-icon-search" size="mini" class="el-input-keyword" placeholder="通过关键字搜索,记得回车哦..." v-model="filters.keywords" @change="keywordsChange" @keyup.enter.native="doSearch"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="mini" @click="doSearch">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="mini" @click="showAddForm">新增</el-button>
        </el-form-item>
      </el-form>

      <!--列表-->
      <el-table slot="list-slot" slot-scope="props" highlight-current-row border fit stripe size="mini" :data="items" :height="props.height" style="width: 100%;" v-loading="listLoading" @selection-change="selectionChange">
        <el-table-column type="selection" align="left" width="35px"></el-table-column>
        <el-table-column prop="name" align="left" fixed label="姓名" width="200px"></el-table-column>
        <el-table-column prop="username" align="left" label="用户名" width="200px"></el-table-column>
        <el-table-column prop="role.name" align="left" label="角色" width="200px"></el-table-column>
        <el-table-column align="left" label="创建时间" min-width="200px">
          <template slot-scope="scope">{{ scope.row.creationTime | formatDateTime}}</template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="220px">
          <template slot-scope="scope">
            <el-button type="info" size="mini" @click="showEditForm(scope.row)">编辑</el-button>
            <el-button type="info" size="mini" @click="showRoleForm(scope.row)" v-show="roleButtonVisible">角色</el-button>
            <el-button type="danger" size="mini" @click="doDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--工具条-->
      <template slot="pagination-slot">
        <el-button type="danger" size="mini" v-if="items.length>0" :disabled="selectedItems.length==0" @click="doBatchDelete">批量删除</el-button>
        <el-button type="primary" size="mini" v-if="hasRecords" :disabled="selectedItems.length==0" @click="doResetPassWord">重置密码</el-button>
        <el-pagination v-if="paginationShow" background style="float:right;" :page-size="pageSize" :current-page="pageIndex" @current-change="doTurnPage" layout="prev, pager, next" :total="totalCount"></el-pagination>
      </template>
    </icare-table>

    <!--编辑表单-->
    <edit :propsVisible="editFormVisible" :propsEditInfo="editInfo" :propsEditMode="editMode" @editClosed="onEditFormClosed" @editCompleted="onEditFormCompleted"></edit>

    <!--角色表单-->
    <role :propsVisible="roleFormVisible" :propsUser="user" @roleFormClosed="onRoleFormClosed" @roleFormCompleted="onRoleFormCompleted" />

  </div>
</template>
<script>
import { getUsers, deleteUsers, resetBatchPassword } from "@/api";
import edit from "./Edit.vue";
import role from "./Role.vue";
import icareTable from '@/components/base/Table'

export default {
  components: {
    edit,
    role,
    icareTable
  },
  props:{
    propPageSize:{
      type: Number,
      default: null
    }
  },
  data() {
    return {
      filters: { keywords: "" },
      items: [],
      pageSize: this.propPageSize == null ? this.GLOBAL.settings.common.pageSize : this.propPageSize,
      pageIndex: 1,
      totalCount: -1,
      selectedItems: [],
      listLoading: false,

      editMode: this.GLOBAL.formAction.ADD,
      editFormVisible: false,
      editInfo: {},

      user: null,
      roleFormVisible: false,
      tableHeight: 300,
      roleButtonVisible: !this.$store.getters.isFrontendAdministrator,

      paginationShow: true
    };
  },
  mounted: function() {
    this.init();
  },
  created: function(){
    this.tableHeight = document.documentElement.clientHeight - 250;
  },
  computed: {},
  methods: {
    init() {
      this.loadItems();
    },
    loadItems() {
      var self = this;
      self.listLoading = true;

      var func = function(k, pi, ps){
          getUsers({
            keywords: k,
            pageIndex: pi,
            pageSize: ps
          }).then(res => {
            if (res.success) {
              var data = res.data;
              if(data.items.length == 0 && self.pageIndex > 1){
                self.pageIndex--;
                func(k, self.pageIndex, ps);                
              }else{
                self.items = data.items;
                self.totalCount = data.totalCount;   
                self.listLoading = false;             
              }
            }
          });
      };

      func(self.filters.keywords, self.pageIndex, self.pageSize);
    },
    hasRecords: function() {
      return this.items.length > 0;
    },
    keywordsChange(val) {
      if (val == "") {
        this.loadItems();
      }
    },
    selectionChange(items) {
      this.selectedItems = items;
    },
    doSearch() {
      this.resetPage();
      this.loadItems();
    },
    doTurnPage(currentChange) {
      this.pageIndex = currentChange;
      this.loadItems();
    },
    doDelete(row) {
      this.$confirm("此操作将永久删除[" + row.name + "], 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.deleteItems(row.id);
        })
        .catch(() => {});
    },
    doBatchDelete() {
      this.$confirm(
        "此操作将删除[" + this.selectedItems.length + "]条数据, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          var ids = "";
          for (var i = 0; i < this.selectedItems.length; i++) {
            ids += this.selectedItems[i].id + ",";
          }
          this.deleteItems(ids);
        })
        .catch(() => {});
    },
    deleteItems(ids) {
      this.listLoading = true;
      var _this = this;
      deleteUsers({ ids: ids }).then(res => {
        _this.listLoading = false;
        if (res.success) {
          _this.$message({ type: "success", message: "操作成功" });
          _this.loadItems();
        }
      });
    },
    doResetPassWord() {
      this.$confirm(
        "此操作将对[" +
          this.selectedItems.length +
          "]条数据重置密码, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          var ids = "";
          for (var i = 0; i < this.selectedItems.length; i++) {
            ids += this.selectedItems[i].id + ",";
          }
          var _this = this;
          resetBatchPassword({ ids: ids }).then(res => {
            _this.listLoading = false;
            if (res.success) {
              _this.$message({ type: "success", message: "操作成功" });
              _this.loadItems();
            }
          });
        })
        .catch(() => {});
    },

    showAddForm() {
      this.editMode = this.GLOBAL.formAction.ADD;
      this.editInfo = {};
      this.editFormVisible = true;
    },
    showEditForm(row) {
      this.editMode = this.GLOBAL.formAction.EDIT;
      this.editInfo = {
        id: row.id,
        name: row.name,
        username: row.username
      };
      this.editFormVisible = true;
    },
    onEditFormClosed() {
      this.editFormVisible = false;
    },
    onEditFormCompleted() {
      this.editFormVisible = false;
      this.loadItems();
    },

    showRoleForm(row) {
      this.user = row;
      this.roleFormVisible = true;
    },
    onRoleFormClosed() {
      this.roleFormVisible = false;
    },
    onRoleFormCompleted() {
      this.roleFormVisible = false;
      this.loadItems();
    },
    resetPage(){
      this.pageIndex = 1;

      this.paginationShow = false;
      this.$nextTick(function () {
        this.paginationShow = true;
      })
    } 
  }
};
</script>
<style lang="scss" scoped>
.el-input-keyword {
  width: 230px;
}
</style>
