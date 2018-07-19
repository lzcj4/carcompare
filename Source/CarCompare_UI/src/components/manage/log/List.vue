<template>
  <div>
    <icare-table> 

      <!--工具条-->
      <el-form slot="search-slot" :inline="true" :model="filters" onsubmit="return false;">
          <el-form-item label="操作人">
            <el-input clearable prefix-icon="el-icon-search" class="el-input-keyword" size="mini" placeholder="通过关键字搜索,记得回车哦..." v-model="filters.username" @change="keywordsChange" @keyup.enter.native="doSearch"></el-input>          
          </el-form-item>
          <el-form-item label="操作类型">
            <el-select  size="mini" v-model="filters.type" value-key="value">
                <el-option :label="item" :value="item" :key="item" v-for="item in logTyps"></el-option>
            </el-select>          
          </el-form-item>
          <el-form-item>
            <span>时间范围：</span>
            <el-date-picker v-model="filters.time" size="mini" type="datetimerange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="mini" @click="doSearch">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="mini" @click="doReset">重置</el-button>
          </el-form-item>
      </el-form>

      <el-table slot="list-slot" slot-scope="props" highlight-current-row border stripe size="mini" :data="items" :height="props.height" :empty-text="GLOBAL.settings.common.emptyText" v-loading="listLoading" @selection-change="selectionChange">
        <el-table-column type="index" label="序号" :index="calcRowNumber" width="80"></el-table-column>
        <el-table-column prop="username" label="操作人" width="200"></el-table-column>
        <el-table-column prop="type" label="操作类型" width="200"></el-table-column>
        <el-table-column prop="description" label="操作内容"></el-table-column>
        <el-table-column label="操作时间" width="180">
          <template slot-scope="scope">{{ scope.row.time | formatDateTime}}</template>
        </el-table-column>            
      </el-table>

      <!--工具条-->
      <template slot="pagination-slot">
        <el-pagination v-if="paginationShow" background style="float:right;" :page-size="pageSize" :current-page="pageIndex" @current-change="doTurnPage" layout="prev, pager, next" :total="totalCount"></el-pagination>
      </template>

    </icare-table>
  </div>

</template>

<script>
import { requestAll, getLogTypes, getLogs, getSearchTimeRange } from "@/api";
import icareTable from '@/components/base/Table'

export default {
  components: {
    icareTable
  },
  data() {
    return {
      filters: { username: "", type: "", time: null },
      items: [],
      pageSize: this.GLOBAL.settings.common.pageSize,
      pageIndex: 1,
      totalCount: -1,
      selectedItems: [],
      listLoading: false,
      paginationShow: true,

      logTyps: [""],
      timeRange: []
    };
  },
  mounted: function() {
    this.init();
  },
  computed: {},
  methods: {
    init() {                 
      let self = this;

      let callback = function(res1, res2){          
          self.logTyps.push.apply(self.logTyps, res1.data);
          
          self.timeRange = [res2.data.start, res2.data.end];
          self.filters.time = self.timeRange;

          self.loadItems();
      }

      requestAll([getLogTypes({}), getSearchTimeRange({})], callback);    
    },
    loadItems() {
      var self = this;
      self.listLoading = true;
   
      let beginTime = "";
      let endTime = "";
      if(this.filters.time != null){
        beginTime = new Date(this.filters.time[0]).toDateTimeString();
        endTime = new Date(this.filters.time[1]).toDateTimeString();
      }
      
      getLogs({
        username: this.filters.username,
        type: encodeURI(this.filters.type),
        beginTime: beginTime,
        endTime: endTime,
        pageIndex: this.pageIndex,
        pageSize: this.pageSize
      }).then(res => {
        self.listLoading = false;
        if (res.success) {          
          self.items = res.data.items;
          self.totalCount = res.data.totalCount;
        }
      });
    },
    hasRecords() {
      return items.length > 0;
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
    doReset(){
      this.filters.username = "";
      this.filters.type = "";
      this.filters.time = this.timeRange;
    },
    doTurnPage(currentChange) {
      this.pageIndex = currentChange;
      this.loadItems();
    },  
    calcRowNumber(index){      
      return (this.pageIndex - 1) * this.pageSize + index + 1
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
  width: 210px;
}
</style>
