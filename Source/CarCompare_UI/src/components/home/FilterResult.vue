<template>
    <div id="filter-result-container">      
        <el-row class="list-container">          
            <div v-for="group in source" :key="group.brand.id" class="group">
                <el-col :span="24" class="brand">
                    {{group.brand.initials}} {{group.brand.name}}
                </el-col>
                <el-col v-for="(item, index) in group.items" :key="item.id" class="item">
                    <el-card class="card" :body-style="{ padding: '10px' }">
                        <div class="card-image" @click="imageDbClickHandler(item)"><img v-lazy="item.picUrl" :onerror="defaultImg" v-image-resize="{ width: 220, height: 165 }"></div>
                        <div class="card-info">                          
                            <span class="series" :title="item.series +'  ' + item.year">{{item.series}}&emsp;{{item.year}}</span>
                            <span class="model" :title="item.model">{{item.model}}</span>
                        </div>
                        <el-button type="primary" class="button-compare" size="mini" @click="contrastShowHandler(item.id)">对比</el-button>
                    </el-card>
                </el-col>
            </div>
        </el-row>
    </div>
</template>
<script>
export default {
  methods: {
    contrastShowHandler: function(itemId) {
      this.$emit("contrastShow", itemId);
    },
    imageDbClickHandler(item){
     this.$emit("openDetail",item);
    }
  },
  props: {
    source: {
      default: function() {
        return [];
      }
    }
  },
  data() {
    return {
      defaultImg: 'this.src="' + require("../../assets/images/default.png") + '"'      
    };
  }
};
</script>

<style lang="scss" scoped>
@import "../../assets/styles/vars.scss";

.list-container {
  padding: 5px;
  .group {
    .brand {
      line-height: 30px;
      margin-top: 10px;
      margin-left: 10px;
      vertical-align: middle;
      font-weight: bold;
      color: $color-primary;
      font-size: 14px;
    }

    .item {
      width: 244px;
      margin: 5px 0px;      
      .card {   
        position: relative;
        padding: 0px;
        margin: 0px;
        border-radius: 0px;
        border: 1px solid #ffffff;
        box-shadow: 0 0 0px rgba(0,0,0,.1);   
        transition: all 0.2s ease-in-out;
                
        .card-image{
          width: 220px;
          height: 165px; 
          overflow:hidden;
          text-align: center;
          vertical-align: middle;
          display: table-cell;
          line-height: 0;
          border:1px #e6e6e6 solid;
        }

        .card-image img {                                    
          -webkit-transform: scale(1);
          -moz-transform: scale(1);
          -ms-transform: scale(1);
          transform: scale(1);
          -webkit-transition: -webkit-transform 0.2s;
          -moz-transition: -moz-transform 0.2s;
          -ms-transition: -moz-transform 0.2s;
          transition: transform 0.2s;
        }

        .card-info {
          padding: 3px 0px;

          .series,.model{
            line-height: 16px;
          }

          .series {  
            width: 100%;          
            display: -webkit-box;
            /*! autoprefixer: off */
            -webkit-box-orient: vertical;
            /* autoprefixer: on */
            -webkit-line-clamp: 1;
            overflow: hidden;            
          }

          .model {
            width: 100%;
            display: block;
            display: -webkit-box;
            /*! autoprefixer: off */
            -webkit-box-orient: vertical;
            /* autoprefixer: on */
            -webkit-line-clamp: 1;
            overflow: hidden;   
          }
        }

        .button-compare {
          height: 20px;
          width: 40px;
          border-radius: 1px;
          padding: 0px;
          position: absolute;
          top: 12px;
          right: 12px;
          display: none;
        }
      }

      .card:hover {    
        border-radius: 0px;
        border: 1px solid $color-primary; 
        box-shadow: 0 0 20px rgba(0,0,0,.2);    

        .button-compare {
          display: block;
          border: 1px #fff solid;
        }
      }

      .card .card-image img:hover{
        -webkit-transform: scale(1.05);
        -moz-transform: scale(1.05);
        -ms-transform: scale(1.05);
        transform: scale(1.05);
      }
    }
  }
}
</style>


