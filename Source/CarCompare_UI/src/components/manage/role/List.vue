<template>
  <div>
    <icare-table>
      <!--工具条-->  
      <el-form slot="search-slot" :inline="true" :model="filters" onsubmit="return false;">
          <el-form-item>
              <el-input clearable prefix-icon="el-icon-search" size="mini" placeholder="通过关键字搜索,记得回车哦..." 
                        v-model="filters.keywords"           
                        @change="keywordsChange"
                        @keyup.enter.native="doSearch"></el-input>
          </el-form-item>
          <el-form-item>
              <el-button type="primary" size="mini" @click="doSearch">查询</el-button>
          </el-form-item>
          <el-form-item>
              <el-button type="primary" size="mini" @click="showAddForm">新增</el-button>
          </el-form-item>
      </el-form> 

      <!--列表-->
      <el-table slot="list-slot" slot-scope="props" highlight-current-row border stripe size="mini" :data="items" :height="props.height" v-loading="listLoading" @selection-change="selectionChange">
        <el-table-column type="selection" align="left" width="35"></el-table-column>      
        <el-table-column prop="name" align="left" label="名称" width="200"></el-table-column>
        <el-table-column prop="code" align="left" label="编码" width="200"></el-table-column>
        <el-table-column prop="description" align="left" label="描述"></el-table-column>
        <el-table-column prop="defaultUrlName" align="left" label="默认页面" width="200"></el-table-column>
        <el-table-column prop="isBackend" align="left" label="后端角色" width="100">        
          <template slot-scope="scope">
            <el-switch v-model="scope.row.isBackend" :disabled="true"></el-switch>
          </template>
        </el-table-column>      
        <el-table-column fixed="right" label="操作" width="220">
          <template slot-scope="scope">
            <el-button type="info" size="mini" @click="showEditForm(scope.row)">编辑</el-button>
            <el-button type="info" size="mini" @click="showPermissionForm(scope.row)">权限</el-button>   
            <el-button type="danger" size="mini" @click="doDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--工具条-->
      <template slot="pagination-slot">
        <el-button type="danger" size="mini" v-if="hasRecords" :disabled="selectedItems.length==0" @click="doBatchDelete">批量删除</el-button>
        <el-pagination v-if="paginationShow" background style="float:right;" :page-size="pageSize" :current-page="pageIndex" @current-change="doTurnPage" layout="prev, pager, next" :total="totalCount"></el-pagination>
      </template>

    </icare-table>  

    <!--编辑表单-->
    <edit :propsVisible="editFormVisible" :propsEditInfo="editInfo" :propsEditMode="editMode" @editClosed="onEditFormClosed" @editCompleted="onEditFormCompleted"></edit>

    <!--权限表单-->
    <permission :propsVisible="permissionFormVisible" :propsRole="permissionRole" @permissionFormClosed="onPermissionFormClosed" @permissionFormCompleted="onPermissionFormCompleted"/>
  </div>
</template>
<script>
  import {getRoles, deleteRoles} from '@/api'
  import edit from './Edit.vue'
  import permission from './Permission.vue'
  import icareTable from '@/components/base/Table'

  export default {
    components:{
      edit,
      permission,
      icareTable
    },
    data() {
      return {
        filters: { keywords: ""},
        items: [],
        pageSize: this.GLOBAL.settings.common.pageSize,
        pageIndex: 1,  
        totalCount: -1,              
        selectedItems: [],
        listLoading: false,

        editMode: this.GLOBAL.formAction.ADD,
        editFormVisible: false,
        editInfo: {},

        permissionRole: null,
        permissionFormVisible: false,

        paginationShow: true
      }
    },
    mounted: function () {
      this.init();
    },
    computed: {

    },
    methods: {   
      init(){
        this.loadItems();
      }, 
      loadItems(){
        var self = this;
        self.listLoading = true;

        var func = function(k, pi, ps){
          getRoles({ keywords: k, pageIndex: pi, pageSize: ps }).then(res=> {            
            if (res.success) {
              var data = res.data;
              if(data.items == 0 && self.pageIndex > 1){
                self.pageIndex--;
                func(k, self.pageIndex, ps);
              }else{
                self.items = data.items;
                self.totalCount = data.totalCount;
                self.listLoading = false;
              }
            }
          })
        };

        func(self.filters.keywords, self.pageIndex, self.pageSize);
      }, 
      hasRecords(){
        return items.length > 0;
      },
      keywordsChange(val){
        if (val == '') {
          this.loadItems();
        }
      },
      selectionChange(items) {
        this.selectedItems = items;
      },      
      doSearch(){
        this.resetPage();
        this.loadItems();
      },     
      doTurnPage(currentChange){
        this.pageIndex = currentChange;
        this.loadItems();
      },
      doDelete(row){
        this.$confirm('此操作将永久删除[' + row.name + '], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteItems(row.id);
        }).catch(() => {
        });
      },      
      doBatchDelete(){
        this.$confirm('此操作将删除[' + this.selectedItems.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          for (var i = 0; i < this.selectedItems.length; i++) {
            ids += this.selectedItems[i].id + ",";
          }
          this.deleteItems(ids);
        }).catch(() => {
        });
      },
      deleteItems(ids){
        this.listLoading = true;
        var _this = this;
        deleteRoles({ ids: ids }).then(res=> {
          _this.listLoading = false;
          if (res.success) {
            _this.$message({type: 'success', message: '操作成功'});
            _this.loadItems();
          }
        })
      },    

      showAddForm(){
        this.editMode = this.GLOBAL.formAction.ADD;
        this.editFormVisible = true;
      },      
      showEditForm(row){
        this.editMode = this.GLOBAL.formAction.EDIT;
        this.editInfo = this.deepCopy(row);
        this.editFormVisible = true;
      }, 
      onEditFormClosed(){
        this.editFormVisible = false;
      },
      onEditFormCompleted(){
        this.editFormVisible = false;
        this.loadItems()
      },

      showPermissionForm(row){
        if(!row.isBackend){
          this.$message.warning('非后端角色不需要设置权限');
          return;
        }
        this.permissionRole = row;
        this.permissionFormVisible = true;
      },
      onPermissionFormClosed(){
        this.permissionFormVisible = false;
      },
      onPermissionFormCompleted(){
        this.permissionFormVisible = false;
      },
      resetPage(){
        this.pageIndex = 1;

        this.paginationShow = false;
        this.$nextTick(function () {
          this.paginationShow = true;
        })
      }       
    }
  }
</script>
