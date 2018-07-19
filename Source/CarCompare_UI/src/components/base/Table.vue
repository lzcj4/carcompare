<template>
  <div>

    <!--工具条-->
    <el-col :span="24" id="search-container" class="search-bar" style="padding-bottom: 0px;">
        <slot name="search-slot"></slot>
    </el-col>

    <!--列表-->
    <el-col :span="24" id="list-container" class="table-bar">
        <slot name="list-slot" :height="tableHeight"></slot>
    </el-col>

    <!--工具条-->
    <el-col :span="24" id="pagination-container" class="pagination-bar">
        <slot name="pagination-slot"></slot>
    </el-col>

  </div>
</template>
<script>
export default {
  components: {

  },
  data() {
    return {
      tableHeight: null
    };
  },
  mounted() {
    var clientHeight = document.documentElement.clientHeight;
    var searchObj = document.getElementById('search-container');
    var paginationObj = document.getElementById('pagination-container');  
    this.tableHeight = clientHeight - searchObj.getBoundingClientRect().top - searchObj.clientHeight - paginationObj.clientHeight - 30;   
  },
  created: function(){

  },
};
</script>
<style lang="scss" scoped>
</style>
