<template>
  <div class="container">
    <span>年款：&nbsp;&nbsp;</span>
    <el-select placeholder="不限" size="mini" v-model="selectedItem" value-key="value" @change="onSelectChanged" style="width:120px;">
        <el-option :label="item.name" :value="item" :key="item.value" v-for="item in itemSource"></el-option>
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
        itemSource: this.source,
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
                name: "年份:" + item.name                
            };
        },
        getTagType: function(){
            return "YEAR";
        },   
        fillFilterParams: function(params){
            if(this.selectedItem){
                params.year = this.selectedItem.value;
            }else{
                params.year = "";
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
.container{
    float: left;
    margin-left: 20px; 
}
</style>


