<template>
  <div class="car-edit-dialog">
    <el-dialog :title="title" :visible.sync="onShow" @open="onOpen" :close-on-click-modal="false" @close="onClose">
      <!-- <edit-cell :car-id="carid" :image-id="imageid" :car-info="carInfo" @car-save-done="onSaveDone" />-->
      <el-row class="car-add-containner">
        <el-col :span="13" class="car-image-selector car-add-cell-col" v-show="!onLoadingDig">
          <ImageSelector ref="child" :image-info="selectedCarImages" v-on:image-delete-id="handleDeleteImageId" v-on:updateImages="updateImages" v-on:updateMarks="updateMarks" />
        </el-col>
        <el-col :span="9" class="car-add-cell-col" v-show="!onLoadingDig">
          <FeatureSelector  @feature:changed="updateFeatures" @feature:reset="resetFeatures" :features-info="slectedCarInfo" :rules="validatesRules" :can-edit-feature="canEditFeature" />
          <div class="btn-save">
            <el-button type="primary" size="small" @click="handleSave" :disabled="!canSave">保存</el-button>
            <el-button v-if="slectedCarInfo.features.id != '' && slectedCarInfo.features.id.length ==36 " class=" btn-delete-detail" type="primary" size="small" @click="handleDelete">删除</el-button>
            <el-button v-if="isAudited ==0" class=" btn-delete-detail" type="primary" size="small" @click="handleAudit" :disabled="!canSave">审核提交</el-button>
          </div>
        </el-col>
        <div class="contrast-mask-ve" v-show="onLoadingDig" v-loading="onLoadingDig" element-loading-text="正在加载..."></div>
      </el-row>
    </el-dialog>
  </div>
</template>
<script>
import FeatureSelector from "../../common/FeatureSelector.vue";
import ImageSelector from "../../common/ImageSelector.vue";
import {
  getCarInfo,
  updateCar,
  uploadImages,
  getCarExists,
  deleteCar,
  updateAndAuditCar
} from "@/api";
import { createEmptyImagesList, createEmptyFeatrues } from "./models";

export default {
  name: "EmptyDialog",
  components: {
    FeatureSelector,
    ImageSelector
  },
  props: {
    visible: {
      type: Boolean,
      default: true
    },
    title: {
      type: String,
      default: "详情"
    },
    carid: {
      type: String,
      default: ""
    },
    imageid: {
      type: Number,
      default: -1
    },
    auditStatus: {
      type: String
    }
  },
  data: function() {
    return {
      onShow: this.$props.visible,

      slectedCarInfo: {
        images: createEmptyImagesList(),
        imageid: 0,
        features: createEmptyFeatrues()
      },
      selectedCarImages: {
        images: createEmptyImagesList(),
        imageid: 0
      },
      canSave: true,
      canEditFeature: true,
      upmarks: {},
      upImages: [],
      deleteImageIds: [],
      onLoadingDig: false,
      validatesRules: {
        brand: false,
        modelyear: false,
        series: false,
        model: false
      },
      isAudited: parseInt(this.$props.auditStatus)
    };
  },
  methods: {
    updateMarks(marks, name) {
      let ups = [];
      if (marks !== undefined && marks.length > 0) {
        marks.map(item => {
          ups.push({
            top: item.top,
            left: item.left,
            width: item.width,
            height: item.height,
            originalWidth: item.originalWidth,
            originalHeight: item.originalHeight
          });
        });
      }
      this.upmarks[name] = ups;
      console.log(this.upmarks);
    },

    updateImages(images) {
      this.upImages = images;
      console.log(images);
    },
    handleDeleteImageId(imageId) {
      console.log("handleDeleteImageId");
      if (this.deleteImageIds.indexOf(imageId) === -1)
        this.deleteImageIds.push(imageId);
    },
    updateFeatures(features, propertyName) {
      this.slectedCarInfo.features = features;
      if (
        this.validatesRules.hasOwnProperty(propertyName) &&
        this.slectedCarInfo.features.hasOwnProperty(propertyName)
      ) {
        console.log(
          "select P :" +
            propertyName +
            "this.slectedCarInfo.features[propertyName] =null :" +
            this.slectedCarInfo.features[propertyName] !=
            ""
        );
        this.validatesRules[propertyName] =
          this.slectedCarInfo.features[propertyName] == "";
      }
      if (propertyName == "model") {
        getCarExists(
          this.slectedCarInfo.features.brand,
          this.slectedCarInfo.features.series,
          this.slectedCarInfo.features.modelyear,
          this.slectedCarInfo.features.model
        ).then(
          function(res) {
            if (
              res.data !== "" &&
              res.data !== this.slectedCarInfo.features.id
            ) {
              console.log(this.slectedCarInfo.features.id);
              this.$message({
                type: "warning",
                message: "该车型已存在，不可重复录入！"
              });
              if (this.canSave) this.canSave = false;
              if (this.canEditFeature) this.canEditFeature = false;
            } else {
              if (!this.canSave) this.canSave = true;
              if (!this.canEditFeature) this.canEditFeature = true;
            }
          }.bind(this)
        );
      } else {
        if (!this.canSave) this.canSave = true;
        if (!this.canEditFeature) this.canEditFeature = true;
      }
      console.log(features);
    },
    resetFeatures() {
      this.$set(this.slectedCarInfo, "features", createEmptyFeatrues());
    },
    onSaveDoning() {
      this.onLoadingDig = false;
      this.onClearState();
      this.onNotifyList();
      this.$root.$emit("car-saved-done");
      this.$message({ type: "success", message: "操作完成", duration: 1000 });
    },
    handleCheckRules() {
      let validateRule = false;
      let ruleNames = Object.getOwnPropertyNames(this.validatesRules).filter(
        rule => {
          return (
            this.validatesRules.hasOwnProperty(rule) &&
            this.slectedCarInfo.features.hasOwnProperty(rule) &&
            rule.substring(0, 2) !== "__"
          );
        }
      );
      if (ruleNames != undefined && ruleNames.length > 0) {
        ruleNames.forEach(rule => {
          this.validatesRules[rule] = this.slectedCarInfo.features[rule] == "";
          if (!validateRule) validateRule = this.validatesRules[rule];
          console.log(
            "validateRule" + validateRule + "" + this.validatesRules[rule]
          );
        });
      }
      return validateRule;
    },
    handleDelete() {
      this.$confirm("是否确定删除数据?", "删除", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.doDelete();
        })
        .catch(() => {
          //取消
        });
    },
    handleSave() {
      if (this.handleCheckRules()) {
        this.$message({ type: "warning", message: "请输入必填信息！" });
        return;
      }
      this.doSave();
    },
    handleAudit() {
      if (this.handleCheckRules()) {
        this.$message({ type: "warning", message: "请输入必填信息！" });
        return;
      }
      this.doUpdateAndAuditCar();
    },
    doDelete() {
      this.onLoadingDig = true;
      deleteCar(this.slectedCarInfo.features.id).then(res => {
        if (res.success) {
          this.onSaveDoning();
        }
        this.onLoadingDig = false;
      });
    },
    doSubmitImages(res) {
      if (res.success) {
        let id = res.data;
        if (this.upImages.length > 0 || this.deleteImageIds.length > 0) {
          let formData = new FormData();
          let parameters;
          this.upImages.map((item, index) => {
            formData.append("files", item.raw);
          });
          formData.append(
            "parameters",
            new Blob([JSON.stringify(this.upmarks)], {
              type: "application/json"
            })
          );
          formData.append(
            "imgeIds",
            new Blob([JSON.stringify(this.deleteImageIds)], {
              type: "application/json"
            })
          );

          uploadImages(formData, "/images/" + id)
            .then(res => {
              this.onSaveDoning();
            })
            .catch(e => {
              this.onLoadingDig = false;
              this.$message({
                type: "error",
                message: "保存图片失败！",
                duration: 1000
              });
            });
        } else {
          this.onSaveDoning();
        }
      } else {
        this.onLoadingDig = false;
      }
    },
    doUpdateAndAuditCar() {
      this.onLoadingDig = true;
      //上传特征值
      updateAndAuditCar(this.slectedCarInfo.features, 1)
        .then(res => this.doSubmitImages(res))
        .catch(e => {
          this.onLoadingDig = false;
          this.$message({
            type: "error",
            message: "保存并审核特征失败！",
            duration: 1000
          });
        });
    },
    doSave() {
      this.onLoadingDig = true;
      //上传特征值
      updateCar(this.slectedCarInfo.features)
        .then(res => this.doSubmitImages(res))
        .catch(e => {
          this.onLoadingDig = false;
          this.$message({
            type: "error",
            message: "保存特征失败！",
            duration: 1000
          });
        });
    },

    onOpen() {
      this.onLoadingDig = true;
      getCarInfo(this.$props.carid)
        .then(res => {
          if (res.success) {
            let tmpInfo = { features: "", imageid: -1, images: [] };
            tmpInfo.imageid = this.$props.imageid;
            if (
              res.data.features != undefined &&
              res.data.features.id != undefined
            ) {
              tmpInfo.features = res.data.features;
            }
            if (
              res.data.pictureOutputs != undefined &&
              res.data.pictureOutputs.length > 0
            ) {
              for (let i = 0; i < res.data.pictureOutputs.length; i++) {
                let tmp = res.data.pictureOutputs[i];
                tmpInfo.images.push({
                  name: tmp.name,
                  url: tmp.smallname,
                  bigurl: tmp.mediumname,
                  id: tmp.id
                });
              }
            }
            this.slectedCarInfo = tmpInfo;
            this.selectedCarImages = {
              images: tmpInfo.images,
              imageid: tmpInfo.imageid
            };
          } else {
            this.$message.warning(res.data);
            this.onShow = false;
          }
          setTimeout(() => {
            this.onLoadingDig = false;
          }, 200);
        })
        .catch(e => {
          setTimeout(() => {
            this.onLoadingDig = false;
          }, 200);
          console.log(e);
        });
    },
    onClose() {
      this.onClearState();
      this.$emit("car:onclose", this.onShow);
    },
    onNotifyList() {
      this.onShow = false;
      this.$emit("car:onclose", this.onShow, true);
    },
    onClearState() {
      this.validatesRules.series = false;
      this.validatesRules.modelyear = false;
      this.validatesRules.brand = false;
      this.validatesRules.model = false;
      this.canSave = true;
      this.canEditFeature = true;

      this.deleteImageIds = [];
      this.upmarks = {};
      this.upImages = [];
      this.deleteImageIds = [];
      this.slectedCarInfo = {
        images: createEmptyImagesList(),
        imageid: 0,
        features: createEmptyFeatrues()
      };
    }
  },
  watch: {
    visible(val) {
      this.onShow = val;
    }
  }
};
</script>

<style lang="scss">
.car-edit-dialog {
  .el-dialog {
    height: 620px;
    width: 940px;
  }
  .el-loading-mask {
    height: 620px;
    width: 940px;
    margin: auto auto;
    background-color: #fff;
    transform: translateY(-13px);
    -webkit-transform: translateY(-23px);
    -ms-transform: translateY(-19px);
  }
  .contrast-mask-ve {
    z-index: 99999;
    width: 100%;
    height: calc(100% - 25px);
    margin-top: 5px;
    margin-bottom: 20px;
    position: relative;
    background-color: #fff;
    display: block;
  }
  .el-dialog__body {
    padding: 0px 0px !important;
    margin-top: 0px;
    height: calc(100% - 16px);
  }

  .car-add-containner {
    height: 100%;
    padding: 0px 0px;
    margin: 0px 0px;
    overflow-y: hidden;
    width: 100%;
  }

  .car-add-cell-col {
    height: calc(100% - 40px);
    margin-top: 20px;
    margin-bottom: 20px;
  }

  .car-image-selector {
    margin-right: 20px;
    margin-left: 20px;
    padding: 0px 0px !important;
  }

  .btn-save {
    float: bottom;
    position: relative;
    bottom: 40px;
    margin: auto auto;
    text-align: center;
  }

  .btn-delete-detail,
  .btn-delete-detail:focus,
  .btn-delete-detail:hover {
    background: #32516c;
    border: transparent;
  }
}
</style>

