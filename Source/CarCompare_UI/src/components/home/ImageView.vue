<template>
  <div class="image-selector image-view" v-loading="onDeleting" element-loading-text="正在删除图片资源...">
    <el-row class="row-image-view">
      <div id="mask">
        <img id="imge-view" :src="this.imagescache[selectedIndex].bigurl ==''? '':this.imagescache[selectedIndex].bigurl" :onerror='"this.src=\"" + require("../../assets/images/default.png") + "\""' draggable="false" v-image-resize="imageSet" />

        <i @click="openAlbumHandler" class="el-icon-delete"></i>

        <div :style="tipCls" class="mark-tips" draggable="false"></div>
      </div>
    </el-row>

    <el-row>
      <div class="img-list">
        <div v-for="(image,index) in imagescache" :id="index" :key="index" :class="index==4?'img-item-last':'img-item'">
          <SingleImageView :index="index" :selected-index="selectedIndex" :image-url="image" v-on:selected="selected" />
        </div>
      </div>
    </el-row>
  </div>
</template>

<script>
import SingleImageView from "./SingleImageView.vue";
import { deleteImage } from "@/api";

export default {
  components: {
    SingleImageView
  },
  props: ["imageInfo", "imageResize"],
  data: function() {
    return {
      selectedIndex: 0,
      imageList: [],
      imagescache: [[], [], [], [], []],
      markinfoscache: [[], [], [], [], []],
      tipCls: {
        display: "none",
        top: 0,
        left: 0,
        width: 0,
        height: 0
      },
      onDeleting: false,
      isPress: false,
      imageSet:
        this.$props.imageResize === undefined
          ? { width: 520, height: 380 }
          : this.$props.imageResize
    };
  },

  mounted: function() {
    this.maskObj = this.$el.querySelector("#mask");
    this.maskTips = this.$el.querySelector(".mark-tips");
    this.imageView = this.$el.querySelector("#imge-view");
    //this.handleMouseDown.bind(this);

    //初始化数据
    this.resetCache();

    //保存完成 清除缓存
    this.$root.$on(
      "car-saved-done",
      function() {
        console.log("car-saved-done");
        this.resetCache();
      }.bind(this)
    );
  },
  watch: {
    imageInfo: {
      handler: function(val) {
        if (val === undefined) return;
        this.resetCache(val.images);
        this.selectedIndex = 0;
      },
      deep: true
    }
  },
  methods: {
    resetCache(images) {
      if (!(images instanceof Array)) images = [];
      let tmps = [];
      this.imageList.splice(0, this.imageList.length);
      for (let i = 0; i < 5; i++) {
        if (i < images.length) {
          this.imageList.push(images[i]);
          tmps.push({
            name: images[i].name,
            url: images[i].url,
            bigurl: images[i].bigurl,
            id: images[i].id
          });
          if (images[i].id == this.$props.imageid) {
            this.selectedIndex = i;
          }
        } else {
          tmps.push({ name: "", url: "", bigurl: "", id: "" });
        }
        this.imagescache = tmps;
        this.markinfoscache = [[], [], [], [], []];
        if (this.$refs.uploads !== undefined)
          this.$refs.uploads.uploadFiles = [];
      }
    },
    selected(image, index) {
      this.selectedIndex = index;
    },
    openAlbumHandler() {
      this.$emit("openAlbum", this.selectedIndex, this.imageList);
    }
  }
};
</script>

<style lang="scss">
.row-image-view {
  height: 380px;
  width: 520px;
  display: table-cell;
  border: 1px #e6e6e6 solid;
  vertical-align: middle;
  text-align: center;
  line-height: 0;
}

.image-selector {
  height: 100%;
}

.image-view {
  #mask {
    line-height: 0;
    width: auto;
    display: inline-block;
    position: relative;
  }
  #mask img {
    border: none;
  }

  .center-div {
    text-align: center;
    text-align: center;
  }

  .mark-button {
    background: #409eff !important;
    margin: 10px auto 15px auto;
    float: right;
  }

  .mark-button-press {
    background: #1373d5 !important;
  }
  .mark-button:hover {
    background: #1373d5 !important;
  }

  #btnmark.is-disabled,
  #btnmark.is-disabled:active,
  #btnmark.is-disabled:focus,
  #btnmark.is-disabled:hover {
    background: #738fac !important;
  }

  .upload-div-btn {
    display: inline-block;
    margin: 10px auto 15px auto;
  }

  .img-list {
    display: block;
    margin-top: 20px;
  }

  .img-item {
    display: inline-block;
    margin-right: 10px;
    margin-top: 0%;
    vertical-align: top;
    width: 18%;
  }

  .img-item-last {
    display: inline-block;
    width: 18%;
  }

  .el-icon-delete {
    position: absolute;
    top: 10px;
    right: 10px;
    display: none;
  }

  .el-icon-delete:before {
    content: url("../../assets/images/frontend/icon_fangda_nor.png");
  }

  .el-icon-delete:hover::before {
    content: url("../../assets/images/frontend/icon_fangda_hover_press.png");
  }

  #mask:hover .el-icon-delete {
    display: block;
  }
}

/*

.mark-info:hover .el-icon-delete {
  display: block;
}

.mark-info {
  background: white;
  position: relative;
  cursor: pointer;
}

.mark-tips {
  background: #000000;
  opacity: 0.2;
  position: relative;
  display: none;
  position: absolute;
  pointer-events: none;
}

.mark-delete {
  color: teal;
  position: absolute;
  top: 2px;
  right: 2px;
  display: none;
}

.el-upload {
  display: inline-block;
}

.btn-upload {
  display: inline-block;
  padding: 6px 20px;
  border-radius: 2px;
  border: 1px #dde9f3 solid;
  color: #1b90f7;
  cursor: pointer;
  background: transparent;
}
.btn-upload:hover {
  border: 1px #1b90f7 solid;
  background: transparent;
  color: #1b90f7;
}

.btn-upload,
.btn-upload:active,
.btn-upload:focus {
  background: transparent;
  color: #1b90f7;
  border: 1px #dde9f3 solid;
}

.btn-upload {
  font-style: normal;
}

.btn-upload span {
  display: block;
  line-height: 16px;
  margin-left: 2px;
  padding-left: 25px;
  background: url(./upload-arrow.png) left center no-repeat;
}
*/
</style>
