<template>
<div class="car-list-container">
<section>
   <el-container>
    <el-aside>
       
      <el-row class="row-search">
        <el-input placeholder="请输入要搜索的品牌名称" v-model="filterText" size="mini" class="input-with-search" clearable>
           <i slot="prefix" class="el-input__icon el-icon-search"></i>
        </el-input> 
      </el-row>

      <el-row>
          <el-tree id="el-tree"
            :props="props"                       
            :load="loadNode"
            :filter-node-method="filterNode"  
            :expand-on-click-node="false"
            @node-click="handleNodeClick"      
            accordion        
            lazy
            highlight-current
            ref="vehicletree">
          </el-tree>
      </el-row>

    </el-aside>
    <el-main id="el-main">
      <el-row class="main-row-filter-result" v-loading="loading">
        <el-col  class="el-col-card-container" v-for="(item) in vehicledatas" :key="item.id">
          <el-card class="card" :body-style="{ padding: '0px' }" @click.native="openEdit(item.id,item.pictureId)">              
              <div class="card-image">
                <img v-lazy="item.picUrl" class="image" :onerror="defaultImg" v-image-resize="{ width: 220, height: 150 }">
              </div>
              <div class="card-bottom">
                <span>{{item.series}}&nbsp;&nbsp;&nbsp;{{item.year}}</span>
              </div>
              <div class="card-bottom">
                <span>{{item.model}}</span>
              </div>
          </el-card>
        </el-col>
				<div class="no-result" v-show="!hasResult">{{emptyText}}</div>
      </el-row>
      
      <el-row type="flex" justify="center">
        <el-pagination v-if="paginationShow" class="main-row-result-pagination" :page-size="pageSize" background style="float:right;"
          :current-page.sync="pageIndex" @current-change="doTurnPage" layout="prev, pager, next" :total="totalCount">          
        </el-pagination>
      </el-row>
    </el-main>
  </el-container>
  <edit-dialog :visible="dialogEditVisible" :carid="selectedCarId" :imageid="selectedPictureId"  @editClosed="handleEditClosed"
   @car:onclose="editClose" :audit-status="this.$props.auditStatus">
  </edit-dialog>
</section>
</div>

</template>

<script>
import {
  getBrandsWithVehicleCount,
  getSeriesWithVehicleCount,
  getModelYearsWithVehicleCount,
  getModelsWithVehicleCount,  
  getVehicles
} from "@/api";
import EditDialog from "./EditDialog.vue";
import defaultrequireImg from "@/assets/images/default.png";

export default {
  components: {
    EditDialog
  },
  props: {
    auditStatus: {
      type: String,
      default: 1
    }
  },
  data() {
    return {
      filterText: "",
      loading: true,
      emptyText: this.GLOBAL.settings.common.emptyText,            
      props: {
        id: "id",
        label: "name",
        children: "children",
        isLeaf: "leaf",
        itemtype: "itemtype"
      },
      vehicledatas: [],
      dialogEditVisible: false,
      selectedCarId: "",
      selectedPictureId: -1,
      pageSize: this.GLOBAL.settings.backend.carListPageSize,
      pageIndex: 1,
      totalCount: -1,
      paramMap: {},
      defaultImg: 'this.src="' + require("../../../assets/images/default.png") + '"',

      paginationShow: true,

      loadNodeArgs: {
        node: null,
        resolve: null
      }
    };
  },
  mounted: function() {
    this.init();
    this.initLayout();
  },

  watch: {
    filterText(val) {      
      this.$refs.vehicletree.filter(val);
    }
  },

  computed: {
    hasResult() {
      return this.vehicledatas.length > 0;
    }
  },

  methods: {
    init() {
      this.loadVehicles();
    },
    initLayout(){
      let height = window.innerHeight - 164;
      document.getElementById("el-tree").style.height = height +"px";
      document.getElementById("el-main").style.height = height + 40 +"px";
    },
    formatItem(item, itemtype, isLeaf){
      return {
        id: item.id,
        name: item.total == 0 ? item.name : item.name + "(" + item.total + ")",
        leaf: isLeaf,
        itemtype: itemtype
      };
    },
    loadNode(node, resolve) {  
        if(this.loadNodeArgs.node == null && this.loadNodeArgs.resolve == null){
          this.loadNodeArgs.node = node;
          this.loadNodeArgs.resolve = resolve;
        }

        if(node.level > 3 ){
          return resolve([]);
        }

        if(node.level == 0){
          getBrandsWithVehicleCount({ vehicleStatus: this.auditStatus }).then(res => {
            if (res.success) {
              var newArrs = [];
              res.data.forEach((item, index) => {
                var newItem = this.formatItem(item, "brand", false);
                newArrs.push(newItem);
              });
              resolve(newArrs);
            }else{
              resolve([]);
            }
          });
        }

        if(node.level == 1){
          var nodeid = node.data.id;
          getSeriesWithVehicleCount({ brandId: nodeid, vehicleStatus: this.auditStatus }).then(res => {
            if (res.success) {
              var newArrs = [];
              res.data.forEach((item, index) => {
                var newItem = this.formatItem(item, "series", false);
                newArrs.push(newItem);
              });
              resolve(newArrs);
            }else{
              resolve([]);
            }
          });          
        }

        if(node.level == 2){
          var nodeid = node.data.id;
          getModelYearsWithVehicleCount({ seriesId: nodeid, vehicleStatus: this.auditStatus }).then(res => {
            if (res.success) {
              var newArrs = [];
              res.data.forEach((item, index) => {
                var newItem = this.formatItem(item, "modelyear", false);
                newArrs.push(newItem);
              });
              resolve(newArrs);
            }else{
              resolve([]);
            }
          });          
        }

        if(node.level == 3){
          var nodeid = node.data.id;
          getModelsWithVehicleCount({ modelyearId: nodeid, vehicleStatus: this.auditStatus }).then(res => {
            if (res.success) {
              var newArrs = [];
              res.data.forEach((item, index) => {
                var newItem = this.formatItem(item, "model", true);
                newArrs.push(newItem);
              });
              resolve(newArrs);
            }else{
              resolve([]);
            }
          });          
        }
    },
    reloadNode(){
        this.loadNodeArgs.node.childNodes = [];    
        this.loadNode(this.loadNodeArgs.node, this.loadNodeArgs.resolve);
    },
    handleNodeClick(data, node) {     
      this.paramMap = {};      
      this.paramMap[node.data.itemtype] = node.data.id;
      this.resetPage();
      this.loadVehicles();      
    },
    handlecarddbClick() {
      this.dialogEditVisible = true;
    },
    handleEditClosed() {
      this.dialogEditVisible = false;
    },    
    filterNode(value, data, node) {           
      if (!value) return true;
      
      let currentNode = node;
      let name = "";

      while(true){   
        if(currentNode == 0){
          break;
        }
        
        if(currentNode.level == 1){          
          name = currentNode.data.name;
          break;
        }

        currentNode = currentNode.parent;
      } 

      let result = name.toLowerCase().indexOf(value.toLowerCase()) != -1;

      return result;
    },
    doTurnPage(currentChange) {
      this.pageIndex = currentChange;
      this.loadVehicles();
    },
    openEdit(id, picId) {
      this.selectedCarId = id;
      this.selectedPictureId = picId;
      this.dialogEditVisible = true;
    },
    editClose(val,refresh) {
      this.dialogEditVisible = val;
      if(refresh){
        this.reloadNode();
        this.loadVehicles();
      }      
    },
    resetPage(){
      this.pageIndex = 1;

      this.paginationShow = false;
      this.$nextTick(function () {
        this.paginationShow = true;
      })
    },

    loadVehicles() {
      this.vehicledatas.splice(0, this.vehicledatas.length);
      this.loading = true;
      this.paramMap["status"] = this.auditStatus;
      getVehicles({
        pageIndex: this.pageIndex,
        pageSize: this.pageSize,
        paramMap: this.paramMap
      }).then(res => {
        this.loading = false;
        if (res.success) {
          var data = res.data;
          this.totalCount = data.totalCount;
          this.vehicledatas = data.items;
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.car-list-container {  
  .el-card {
    border-radius: 4px;
    height: 220px;
    padding: 10px;
  }
  .el-card__body {
    padding: 0px;
  }
  .card-bottom {
    margin-top: 13px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 1;
    overflow: hidden;
  }
  .row-search{
    margin-bottom: 10px;
  }
  .input-with-search {
    width: 100%;
    text-align: left;
  }
  .el-col-card-container {
    width: 245px;
    margin: 0px 12px 12px 0px;
    padding: 0px;
  }
  .no-result {
    line-height: 200px;
    text-align: center;
    vertical-align: middle;
  }
  .main-row-result-pagination {
    line-height: 60px;
    background: #fff;
    text-align: center;
    padding-top: 30px;
  }
  .card {
    position: relative;
    padding: "0px";
    margin: "5px";
  }
  .card:hover {
    border: 1px solid #1b90f7;
  }

  .card-image{
    width:220px;
    height: 150px;
    display: table-cell;
    border:1px #e6e6e6 solid;
    text-align: center;
    vertical-align: middle;
    line-height: 0px;
  }
  .image {
    // width: 100%;
    // height: 150px;
  }

  /* .card:hover .tooltiptext {
  visibility: visible;
}
.card .tooltiptext {
  visibility: hidden;
  width: 120px;
  background-color: black;
  color: #fff;
  text-align: center;
  padding: 5px 0;
  border-radius: 6px;
  position: absolute;
  z-index: 1;
}*/ /*鼠标移动上去后显示提示框 */
}
</style>
<style lang="scss">
.car-list-container{
  .el-tree{
    width: 100%;
    overflow-x:auto;
  }
  .el-tree>.el-tree-node{
    min-width:100%;
    display: inline-block;
  }
  .el-main{
    overflow-y:auto;
    padding-top:0px;
  }
}
</style>


