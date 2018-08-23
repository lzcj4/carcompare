<template>
  <div class="container">
    <div class="header">

      <el-row class="header-row">
        <el-col :span="10" class="logo"><img src="../assets/images/frontend/logo.png"/></el-col>
        <el-col :span="4" class="userinfo">
          <top-user parent="home" :enable-home-menu="false" :enable-manage-menu="true"></top-user>
        </el-col>
      </el-row>

    </div>
    <div class="main">

      <el-row class="main-row">
        <el-row class="main-row-filter">
          <el-col class="upload-container">
            <image-selector ref="imageSelector" />
          </el-col>
          <el-col class="filter-container">
            <lazy-render :time="300">
                <filter-condition @changed="onFilterChanged" />
            </lazy-render>                        
          </el-col>
        </el-row>
        <el-row class="main-row-filter-collapse-bar">
          <button @click="onCollapseClick"/>
        </el-row>
        <el-row class="main-row-filter-tag">
          <el-col :span="20">
            <filter-tag-bar ref="filterTagBar" />
          </el-col>
          <el-col :span="4" class="filter-result-count">
            <span>搜索结果：共 {{totalCount}} 条</span>
          </el-col>
        </el-row>
        <el-row class="main-row-separate-line">
        </el-row>
        <el-row class="main-row-filter-result" v-loading="loading">
          <filter-result ref="filterResult" :source="items" v-show="hasResult" v-on:contrastShow="contrastShow" @openDetail="handlecarddbClick" />
          <div class="no-result" v-show="showNoResult">{{emptyText}}</div>
        </el-row>
        <el-row class="main-row-result-pagination">
          <el-pagination v-if="paginationShow" background :page-sizes="[this.GLOBAL.settings.frontend.carListPageSize, 50, 100, 200, 300]" :page-size="pageSize" :current-page="pageIndex" :total="totalCount" @size-change="sizeChangeHandler" @current-change="doTurnPage" layout="sizes, prev, pager, next, jumper"></el-pagination>
        </el-row>
      </el-row>

    </div>
    <common-footer></common-footer>

    <vehicle-contrast ref="vehicleContrast" :vehicleId="vehicleId" :originalUrl="originalUrl" @modelClick="handleModelClick"></vehicle-contrast>    

     <CarDetail :visible="dialogEditVisible" :carid="selectedCarId" :imageid="selectedPictureId"  @editClosed="handleEditClosed" @car:onclose="editClose" >
  </CarDetail>

  </div>
</template>
<script>
import { searchVehicles } from "@/api";
import topUser from "./common/TopUser.vue";
import filterCondition from "./home/FilterCondition.vue";
import filterTagBar from "./home/FilterTagBar.vue";
import filterResult from "./home/FilterResult.vue";
import imageSelector from "./home/ImageSelector.vue";
import vehicleContrast from "@/components/common/VehicleContrast.vue";
import lazyRender from "@/components/base/LazyRender.vue"
import CarDetail from "./home/CarDetail.vue";
import commonFooter from "@/components/common/CommonFooter.vue"

export default {
  components: {
    topUser,
    filterCondition,
    filterTagBar,
    filterResult,
    imageSelector,
    vehicleContrast,    
    lazyRender,
    CarDetail,
    commonFooter
  },
  data() {
    return {
      params: {},
      pageSize: this.GLOBAL.settings.frontend.carListPageSize,
      pageIndex: 1,
      items: [],
      totalCount: 0,
      loading: true,
      emptyText: this.GLOBAL.settings.common.emptyText,
      selectedImage: null,   
      vehicleId: "",
      originalUrl: "",

      dialogEditVisible: false,
      selectedCarId: "",
      selectedPictureId: -1,

      paginationShow: true
    };
  },
  methods: {
    doFilter: function() {
      const self = this;

      self.loading = true;
      self.params["status"] = self.GLOBAL.vehicleAuditStatus.AUDITED; //显示审核通过的车型         

      searchVehicles({
        paramMap: JSON.stringify(self.params).replace(/\"/g,"'"),
        pageIndex: self.pageIndex,
        pageSize: self.pageSize
      }).then(res => {
        self.loading = false;
        if (res.success) {
          self.totalCount = res.data.totalCount;
          self.items = res.data.items;
        }
      });
    },
    onFilterChanged: function(params) {
      this.params = params;
      this.resetPage();
      this.doFilter();
    },
    doTurnPage: function(currentChange) {
      this.pageIndex = currentChange;
      this.doFilter();
      this.resetScrollBar();
    },
    sizeChangeHandler: function(currentSize){
      this.pageSize = currentSize;
      this.resetPage();
      this.doFilter();      
      this.resetScrollBar();
    },
    onCollapseClick: function() {    
      $(".main-row-filter").animate({height: 'toggle'});
    },
    contrastShow: function(itemId) {
      this.vehicleId = itemId;
      if ((this.$refs.imageSelector.picZoomEnable == true)) {
        this.originalUrl = this.$refs.imageSelector.imageSource;
      } else {
        this.originalUrl = "";
      }

      this.$refs.vehicleContrast.show();
    },
    resetScrollBar: function(){
      var ele = $(".main-row-filter-tag")[0];
      var top = ele.offsetTop + 70;      
      $("#app").animate({scrollTop: top}, 300);
    },    
    editClose(val,refresh) {
      this.dialogEditVisible = val;
      if(refresh){
        this.reloadNode();
        this.loadVehicles();
      }  
    },
    handleModelClick(itemId){
      this.selectedCarId = itemId;
      this.dialogEditVisible = true;
    },
    handlecarddbClick(item) {
      this.selectedCarId = item.id;
      this.dialogEditVisible = true;
    }, 
    handleEditClosed() {
      this.dialogEditVisible = false;
    },    
    resetPage(){
      this.pageIndex = 1;

      this.paginationShow = false;
      this.$nextTick(function () {
        this.paginationShow = true;
      })
    } 
  },
  mounted() {
    this.doFilter();
  },
  computed: {
    user() {
      return this.$store.state.user;
    },
    hasResult() {
      return this.items.length > 0;
    },
    showPagination() {
      return this.totalCount > this.pageSize;
    },
    showPlus() {
      return this.selectedImage == null;
    },
    showImage() {
      return this.selectedImage != null;
    },
    showNoResult(){
      return !this.hasResult && !this.loading;
    }
  }
};
</script>

<style lang="scss">
@import "../assets/styles/frontend-common.scss";
</style>
<style lang="scss" scoped>
@import "../assets/styles/vars.scss";
@import "./Home.scss";
</style>


