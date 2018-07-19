<template>
  <div class="logo-shape-container">
    <span>车标框形：&nbsp;&nbsp;</span>
    <el-select  size="mini" v-model="selectedItem" value-key="value" @change="onSelectChanged">
        <el-option :label="item.name" :value="item" :key="item.value" v-for="item in source"></el-option>
    </el-select>
  </div> 
</template>
<script>
import Bus from '@/components/common/Bus.vue'

export default {
  props: {
      source: {
        default: function(){
            return  [];
        }
      }
  },
  data(){
        return {            
            selectedItem: null
        }
  },
  methods: {
        onSelectChanged: function(item){          
            this.selectedItem = item;
            let tag = this.buildTag(item);
            Bus.$emit('filterItemSelected', tag);          
        },
        buildTag: function(item){
            return {
                type: this.getTagType(),
                name: "车标框形:" + item.name                
            };
        },
        getTagType: function(){
            return "CBKX";
        },   
        fillFilterParams: function(params){
            if(this.selectedItem){
                params.chebiaokuangxing = this.selectedItem.value;
            }else{
                params.chebiaokuangxing = "";
            }                  
        }           
  },
  computed: {

  },
  mounted() {
        Bus.$on('filterBarTagRemoved',(tag) => {
            if(tag.type == this.getTagType()){
                this.selectedItem = null;
            }
        });      
  }
}
</script>
<style lang="scss" scoped>
.logo-shape-container{
    float: left;
    span{
        font-size: 12px;
    }
}
</style>
<style lang="scss">
.logo-shape-container{
    .el-select{
        width:120px;
    }
}
</style>
