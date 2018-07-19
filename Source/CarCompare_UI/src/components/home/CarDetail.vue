<template>
  <div class="car-view-dialog">
    <el-dialog :title="title" :visible.sync="onShow" @open="onOpen" :close-on-click-modal="false" @close="onClose">
      <el-row class="car-add-containner" >
        <el-col :span="13" class="car-image-selector car-add-cell-col" v-show="!onLoadingDig">
          <ImageView  ref="child" :image-info="selectedCarImages" v-on:openAlbum="openAlbumHandler" />
        </el-col>
        <el-col :span="9" class="car-add-cell-col" v-show="!onLoadingDig">
          <FeatureView  :features-info="slectedCarInfo" :rules="validatesRules" :can-edit-feature="canEditFeature"/>         
        </el-col>
        <div class="contrast-mask-v" v-show="onLoadingDig" v-loading="onLoadingDig" element-loading-text="正在加载..."></div>
      </el-row>
    </el-dialog>

    <Album :img-list="albumImgList" :index="currentIndex" :visiable="onShowAlbum" v-on:albumClosed="ablbumClosedHandler" />

  </div>
</template>
<script>
import FeatureView from "./FeatureView.vue";
import ImageView from "./ImageView.vue";
import Album from "./Album.vue";

import {
  getCarInfo,
  updateCar,
  uploadImages,
  getCarExists,
  deleteCar,
  updateAndAuditCar
} from "@/api";
import {
  createEmptyImagesList,
  createEmptyFeatrues
} from "../manage/car/models";

export default {
  name: "CarDetail",
  components: {
    FeatureView,
    ImageView,
    Album
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

      currentIndex: 0,
      onShowAlbum: false,
      albumImgList: []
    };
  },
  methods: {
    openAlbumHandler(index, imgList) {
      if (imgList.length <= 0) return;
      let tmps = [];
      for (let i = 0; i < imgList.length; i++) {
        if (imgList[i] != "" && imgList[i].oriurl != undefined) {
          tmps.push(imgList[i].oriurl);
        } else {
          if (index > i) index--;
        }
      }
      this.albumImgList = tmps;
      this.currentIndex = index;
      this.onShowAlbum = true;
    },
    ablbumClosedHandler() {
      this.onShowAlbum = false;
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
                  oriurl: tmp.orginnalname,
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

          //setTimeout(() => {
            this.onLoadingDig = false;
          //}, 600);
        })
        .catch(e => {
          //setTimeout(() => {
            this.onLoadingDig = false;
         // }, 600);
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
.car-view-dialog {
  .el-dialog {
    height: 580px;
    width: 940px;
  }
  .el-loading-mask {
    height: 580px;
    width: 940px;
    margin: auto auto;
    background-color: #fff;
    transform: translateY(-33px);
    -webkit-transform: translateY(-43px);
    -ms-transform: translateY(-39px);
  }

  .contrast-mask-v {
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
    overflow: hidden;
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

