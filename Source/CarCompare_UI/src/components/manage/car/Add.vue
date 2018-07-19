<template >
  <div ref="bb" class="add-car-containner">
    <el-row class="car-add-containner" v-loading="onLoading" element-loading-text="正在保存...">
      <el-col :span="13" class="car-image-selector car-add-cell-col">
        <ImageSelector ref="child" v-on:image-delete-id="handleDeleteImageId" v-on:updateImages="updateImages" v-on:updateMarks="updateMarks" />
      </el-col>
      <el-col :span="9" class="car-add-cell-col"  v-if="showF">
        <FeatureSelector @feature:changed="updateFeatures" @feature:reset="resetFeatures" :features-info="slectedCarInfo" :rules="validatesRules" :can-edit-feature="canEditFeature" />
        <el-button class="btn-save" type="primary" size="small" @click="handleSave" :disabled="!canSave">保存</el-button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import FeatureSelector from "../../common/FeatureSelector.vue";
import ImageSelector from "../../common/ImageSelector.vue";
import { createCar, uploadImages, getCarExists, deleteCar } from "@/api";
import { createEmptyImagesList, createEmptyFeatrues } from "./models";
import { isChrome } from "@/utils/utils"

export default {
  name: "Add",
  components: {
    FeatureSelector,
    ImageSelector
  },
  data: function() {
    return {
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
      onLoading: false,
      validatesRules: {
        brand: false,
        modelyear: false,
        series: false,
        model: false
      },
      showF: false
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
        ).then(res => {
          if (res.data !=="") {
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
        });
      } else {
        if (!this.canSave) this.canSave = true;
        if (!this.canEditFeature) this.canEditFeature = true;
      }
      console.log(features);
    },
    resetFeatures() {
      this.$set(this.slectedCarInfo, "features", createEmptyFeatrues());
    },
    onSaveDone() {
      this.onLoading = false;
      this.deleteImageIds = [];
      this.slectedCarInfo.features.brand = "";
      this.slectedCarInfo.features.modelyear = "";
      this.slectedCarInfo.features.series = "";
      this.slectedCarInfo.features.model = "";
      this.slectedCarInfo.features.id = "";
      this.upmarks = {};
      this.upImages = [];
      this.deleteImageIds = [];

      this.$message({ type: "success", message: "操作完成", duration: 1000 });
      this.$root.$emit("car-saved-done");
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

    handleSave() {
      if (this.handleCheckRules()) {
        this.$message({ type: "warning", message: "请输入必填信息！" });
        return;
      }
      this.doSave();
    },

    doSave() {
      this.onLoading = true;
      //上传特征值
      createCar(this.slectedCarInfo.features)
        .then(res => {
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
                  this.onSaveDone();
                })
                .catch(e => {
                  this.onLoading = false;
                  this.$message({
                    type: "error",
                    message: "保存图片失败！",
                    duration: 1000
                  });
                });
            } else {
              this.onSaveDone();
            }
          } else {
            this.onLoading = false;
          }
        })
        .catch(e => {
          this.onLoading = false;
          this.$message({
            type: "error",
            message: "保存特征失败！",
            duration: 1000
          });
        });
    },

    resetSize: function() {
      this.$refs["bb"].style.height =
        document.documentElement.clientHeight - 125 + "px";
    }
  },
  mounted: function() {
    let time = isChrome() ? 0 : 200;
    debugger

    setTimeout(
      function() {
        this.showF = true;
      }.bind(this),
      time
    );
    this.resetSize();
    window.addEventListener("resize", this.resetSize);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.resetSize);
  }
};
</script>

<style>
.add-car-containner {
  padding: 0px 0px;
  margin: 0px 0px;
  overflow-y: hidden;
  width: 1000px;
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
  left: 50%;
  transform: translateX(-50%);
}

.btn-delete-detail,
.btn-delete-detail:focus,
.btn-delete-detail:hover {
  background: #32516c;
  border: transparent;
}
</style>


