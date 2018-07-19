<template>
  <el-dialog class="contrast-dialog" :title="title" :visible.sync="visible" :width="width"  :close-on-click-modal="false" :before-close="onClose" append-to-body>
    <div class="contrast-container">      
      <div id="contrast-box">
        <div class="pic-boxleft">
          <pic-zoom :url="originalUrl" :scale="2.5" :onerror="defaultImg" position="left" ></pic-zoom>
        </div>
        <div class="pic-boxright">
          <pic-zoom :url="contrastUrl" :scale="2.5" ref="constrastBox" :onerror="defaultImg" ></pic-zoom>
        </div>
      </div>
      <div id="contrast-table" v-show="!loading">
        <el-table :data="contrast" header-align="center" border size="mini" :highlight-current-row="false">        
          <el-table-column label="车型" width="190" :resizable="false" header-align="center" align="center">
            <template slot-scope="scope">
              <div :title="scope.row.vehicleType" class="cell-model" @click="modelClick">{{scope.row.vehicleType}}</div>
            </template>            
            </el-table-column>
          </el-table-column>
          <el-table-column :label="labelFirst" width="140" :resizable="false" :render-header="handleHeaderOnRender" header-align="center" align="center">
            <template slot-scope="scope">
              <img class="img-small" :src="scope.row.srcFirst" @click="cellClick(0)" :onerror="defaultImg" v-image-resize="{ width: 128, height: 96 }">          
            </template>
          </el-table-column>
          <el-table-column :label="labelSecond" width="140" :resizable="false" :render-header="handleHeaderOnRender" header-align="center" align="center">
            <template slot-scope="scope">
              <img class="img-small" :src="scope.row.srcSecond" @click="cellClick(1)" :onerror="defaultImg" v-image-resize="{ width: 128, height: 96 }">
            </template>
          </el-table-column>
          <el-table-column :label="labelThird" width="140" :resizable="false" :render-header="handleHeaderOnRender" header-align="center" align="center">
            <template slot-scope="scope">
              <img class="img-small" :src="scope.row.srcThird" @click="cellClick(2)" :onerror="defaultImg" v-image-resize="{ width: 128, height: 96 }">
            </template>
          </el-table-column>
          <el-table-column :label="labelFourth" width="140" :resizable="false" :render-header="handleHeaderOnRender" header-align="center" align="center">
            <template slot-scope="scope">
              <img class="img-small" :src="scope.row.srcFourth" @click="cellClick(3)" :onerror="defaultImg" v-image-resize="{ width: 128, height: 96 }">
            </template>
          </el-table-column>
          <el-table-column :label="labelFifth" width="140" :resizable="false" :render-header="handleHeaderOnRender" header-align="center" align="center">
            <template slot-scope="scope">
              <img class="img-small" :src="scope.row.srcFifth" @click="cellClick(4)" :onerror="defaultImg" v-image-resize="{ width: 128, height: 96 }">
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="contrast-mask" v-show="loading" v-loading="loading">
        
    </div>
  </el-dialog>
</template>

<script>
import PicZoom from "@/components/base/PicZoom";
import defaultcar from "@/assets/images/defaultcar.jpg";
import { getCarInfo } from "@/api";
import { getVehicleDescriptionById } from "@/api";
export default {
  name: "VehicleContrast",
  components: {
    PicZoom
  },
  props: ["vehicleId", "originalUrl"],
  data() {
    return {
      title: "车型对比",
      visible: false,
      loading: false,
      width: "940px",
      //如果图片获取失败显示图片
      defaultImg: 'this.src="' + require("../../assets/images/default.png") + '"',
      pictureData: [],
      contrastUrl: defaultcar,
      contrast: [
        {
          vehicleType: "",
          srcFirst: defaultcar,
          srcSecond: defaultcar,
          srcThird: defaultcar,
          srcFourth: defaultcar,
          srcFifth: defaultcar
        }
      ],
      labelFirst: "",
      labelSecond: "",
      labelThird: "",
      labelFourth: "",
      labelFifth: ""
    };
  },
  mounted() {    
  },
  methods: {
    initBox() {      
      if (!this.vehicleId) return;
      this.loading = true;
      getCarInfo(this.vehicleId).then(res => {        
        if (res.success) {
          let data = res.data.pictureOutputs;
          this.pictureData = data;
          if (data&&data[0]) {
            this.contrastUrl = data[0].orginnalname;
            this.contrast[0].srcFirst = data[0].smallname;
            this.labelFirst = data[0].name;
          } else {
            this.contrastUrl = defaultcar;
            this.contrast[0].srcFirst = defaultcar;
            this.labelFirst = "";
          }
          if (data&&data[1]) {
            this.contrast[0].srcSecond = data[1].smallname;
            this.labelSecond = data[1].name;
          } else {
            this.contrast[0].srcSecond = defaultcar;
            this.labelSecond = "";
          }
          if (data&&data[2]) {
            this.contrast[0].srcThird = data[2].smallname;
            this.labelThird = data[2].name;
          } else {
            this.contrast[0].srcThird = defaultcar;
            this.labelThird = "";
          }
          if (data&&data[3]) {
            this.contrast[0].srcFourth = data[3].smallname;
            this.labelFourth = data[3].name;
          } else {
            this.contrast[0].srcFourth = defaultcar;
            this.labelFourth = "";
          }
          if (data&&data[4]) {
            this.contrast[0].srcFifth = data[4].smallname;
            this.labelFifth = data[4].name;
          } else {
            this.contrast[0].srcFifth = defaultcar;
            this.labelFifth = "";
          }
        }

        let self = this;
        setTimeout(() => {
          self.loading = false;
        }, 400);
      });
      getVehicleDescriptionById(this.vehicleId).then(res => {
        if (res.success) {
          this.contrast[0].vehicleType = res.data.description;
        }
      });
    },    
    show() {
      this.visible = true;
    },
    onClose() {     
      this.visible = false;      
    },
    cellClick(picUrl) {
      if (this.pictureData[picUrl]) {
        this.contrastUrl = this.pictureData[picUrl].orginnalname;
      }
    },
    modelClick(){
      this.$emit('modelClick', this.vehicleId);
    },
    handleHeaderOnRender(h, { column, $index }){
      return <span title={column.label}>{column.label}</span>;
    }    
  },
  watch: {
    vehicleId(val) {
      if (val != undefined) {        
        this.initBox();
      }
    }
  }
};
</script>

<style>
.contrast-dialog{
  position: relative;
}
.contrast-mask{
  z-index: 99999;
  height: 520px;
  width: 900px;
  top:40px;
  left:15px;
  position: absolute;
  background-color: #fff;
}
.contrast-container{
  height: 520px;
}

#contrast-box {
  width: 892px;
  height: 340px;
  background: #f6f8fc;
}
#contrast-table {
  margin-top: 15px;
  width: 892px;
  height: 180px;
}
#contrast-table .cell-model{
  display: -webkit-box;
  /*! autoprefixer: off */
  -webkit-box-orient: vertical;
  /* autoprefixer: on */
  -webkit-line-clamp: 2;
  overflow: hidden;    
  text-align: left;
  text-decoration-line: underline;
  color:#1b90f7;
  cursor: pointer;
}
#contrast-table .el-table .cell{
  padding-left: 5px;
  text-align: center;
  vertical-align: middle;  
  display: -webkit-box;
  /*! autoprefixer: off */
  -webkit-box-orient: vertical;
  /* autoprefixer: on */
  -webkit-line-clamp: 1;
  overflow: hidden;      
}
#contrast-table .el-table th{
  padding: 2px;
  background: #f6f8fc;
}
.pic-boxleft {
  width: 424px;
  height: 320px;
  border: 1px solid #e5eaf7;
  float: left;
  margin-top: 10px;
  margin-left: 12px;
  margin-bottom: 10px;
}
.pic-boxright {
  width: 424px;
  height: 320px;
  border: 1px solid #e5eaf7;
  float: right;
  margin-top: 10px;
  margin-right: 12px;
  margin-bottom: 10px;
}
.img-small {
  display: block;
}
img:hover {
  cursor: pointer;
}
</style>

