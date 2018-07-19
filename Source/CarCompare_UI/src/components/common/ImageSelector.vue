<template>
  <div class="image-selector" v-loading="onDeleting" element-loading-text="正在删除图片资源...">
    <el-row class="row-image-view">
      <div id="mask">
        <img  id="imge-view" :src="this.imagescache[selectedIndex].bigurl ==''? '':this.imagescache[selectedIndex].bigurl" :onerror='"this.src=\"" + require("../../assets/images/default.png") + "\""' draggable="false" v-image-resize="imageSet" />
        <div v-for="mark in this.markinfoscache[this.selectedIndex]" @mousedown.stop="" v-bind:style="mark.cls" class="mark-info">
          <i @click="handleDelMark(mark.id)" class="el-icon-delete"></i>
        </div>
        <div :style="tipCls" class="mark-tips" draggable="false"></div>
      </div>
    </el-row>
    <el-row class="center-div">

      <el-upload ref="uploads" class="upload-div-btn" action="" :auto-upload="false" :show-file-list="false" multiple :file-list="imageList" :limit="5" accept="image/png, image/jpeg" :on-change="handleAllAction" :on-exceed="handleAllExceed">
        <el-button size="small" class="btn-upload" type="primary">
          点击上传
        </el-button>
      </el-upload>

      <el-button id="btnmark" size="small" type="primary" class="mark-button" @click="registMaskEvent" :class="{'mark-button-press':isPress}" :disabled="this.imagescache[selectedIndex].id !== -1" right>打白条</el-button>

    </el-row>
    <el-row>
      <div class="img-list">
        <div v-for="(image,index) in imagescache" :id="index" :key="index" :class="index==4?'img-item-last':'img-item'">
          <single-image-selector :index="index" :selected-index="selectedIndex" :image-url="image" v-on:add="add" v-on:selected="selected" v-on:deleted="deleted" />
        </div>
      </div>
    </el-row>
  </div>
</template>

<script>
import SingleImageSelector from "../base/SingleImageSelector.vue";
import { deleteImage } from "@/api";

export default {
  components: {
    SingleImageSelector
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
    this.imageView= this.$el.querySelector("#imge-view");
    this.handleMouseDown.bind(this);

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
      },
      deep: true
    },
    selectedIndex(val) {
      this.unRegistMaskEvent();
    }
  },
  methods: {
    resetCache(images) {
      this.unRegistMaskEvent();
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
    handleAllAction(file, fileList) {
      if (fileList.length <= 0) return;
      for (var i = 0; i < fileList.length; i++) {
        if (fileList[i].raw != undefined) {
          let exists = this.imagescache.findIndex(f => {
            return fileList[i].url === f.url;
          });
          if (exists == -1) {
            for (let j = 0; j < this.imagescache.length; j++) {
              if (this.imagescache[j].url == "") {
                this.imagescache[j] = {
                  name: fileList[i].name,
                  url: fileList[i].url,
                  bigurl: fileList[i].url,
                  id: -1
                };
                break;
              }
            }
          }
          exists = this.imageList.findIndex(f => {
            return fileList[i].url === f.url;
          });
          if (exists == -1) {
            this.imageList.push(fileList[i]);
          }
        }
      }
      this.$emit("updateImages", this.getForuploadFile());
    },
    getForuploadFile() {
      let foruploads = [];
      this.imageList.map(f => {
        if (f.id == undefined) foruploads.push(f);
      });
      return foruploads;
    },
    handleAllExceed: function(files, fileList) {
      this.$message.warning(
        `当前限制选择 5 个文件，本次选择了 ${
          files.length
        } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      );
    },
    add(file, index) {
      this.imageList.push(file);
      this.imagescache[index] = {
        name: file.name,
        url: file.url,
        bigurl: file.url,
        id: -1
      };
      this.selectedIndex = index;
      this.$emit("updateImages", this.getForuploadFile());
    },
    deleted(image, index, callback) {
      this.$confirm("是否确认删除?", "删除", {
        confirmButtonText: "确认",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          if (image.id !== -1) {
            // this.onDeleting = true;
            console.log("image-delete-id");
            this.$emit("image-delete-id", image.id);
            this.deleteCache(image, index);
            //注释掉  直接删除图片
            //deleteImage(image.id).then(res => {
            //  if (res.success) {
            //    console.log(res.data);
            //    this.deleteCache(image, index);
            //    this.onDeleting = false;
            //  } else {
            //    this.$message.warning(`删除失败！`);
            //    this.onDeleting = false;
            //  }
            //});
          } else {
            this.deleteCache(image, index);
          }
          if (callback != null && callback !== undefined) callback();
        })
        .catch(() => {
          //取消
        });
    },
    deleteCache(image, index) {
      var idx = this.imageList.findIndex(function(item) {
        return item.url === image.url;
      });
      if (idx >= 0) {
        this.imageList.splice(idx, 1);
      }
      this.markinfoscache[index].splice(0, this.markinfoscache[index].length);
      this.$emit(
        "updateMarks",
        this.markinfoscache[this.selectedIndex],
        this.imagescache[this.selectedIndex].name
      );
      this.imagescache[index] = { name: "", url: "", bigurl: "", id: "" };
      this.selectedIndex = 0;

      this.unRegistMaskEvent();
      this.$emit("updateImages", this.getForuploadFile());
    },
    selected(image, index) {
      this.selectedIndex = index;
    },
    registMaskEvent: function() {
      console.log(this.maskObj);
      if (this.maskObj.onmousedown != null) {
        this.maskObj.onmousedown = null;
        this.isPress = false;
        this.imageView.style.cursor = "default";
      } else {
        this.maskObj.onmousedown = this.handleMouseDown;
        this.isPress = true;
        this.imageView.style.cursor = "crosshair";
      }
      console.log("isPress:" + this.isPress);
    },
    unRegistMaskEvent: function() {
      console.log(this.maskObj);
      this.imageView.style.cursor = "default";
      this.isPress = false;
      if (this.maskObj.onmousedown != null) {
        this.maskObj.onmousedown = null;
        this.maskObj.onmousemove = null;
        this.maskObj.onmouseup = null;
      }
    },
    handleMouseDown: function(e) {
      let bx = e.offsetX,
        by = e.offsetY;
      this.maskWidth = this.maskObj.clientWidth;
      this.maskHeight = this.maskObj.clientHeight;
      //this.maskObj.style.cursor = "move";
      this.maskObj.onmousemove = function(e) {
        console.log(this.maskObj.style.cursor);
        let mx = e.offsetX,
          my = e.offsetY;
        if (
          this.maskWidth >= mx &&
          this.maskHeight >= my &&
          mx - bx > 10 &&
          my - by > 10
        ) {
          var markcls = {};
          markcls.top = by + "px";
          markcls.display = "block";
          markcls.left = bx + "px";
          markcls.width = mx - bx + "px";
          markcls.height = my - by + "px";
          this.tipCls = markcls;
        } else {
          this.tipCls.display = "none";
        }
      }.bind(this);

      this.maskObj.onmouseup = function(e) {
        this.maskObj.onmousemove = null;
        this.maskObj.onmouseup = null;
        this.tipCls.display = "none";
        let ex = e.offsetX,
          ey = e.offsetY;
        let wid = ex - bx,
          hei = ey - by;
        let id = this.markinfoscache[this.selectedIndex].length + 1;

        if (wid < 10 || hei < 10) {
          e.preventDefault();
          return;
        }

        var mark = {};
        mark.id = id;
        mark.top = by;
        mark.left = bx;
        mark.width = wid;
        mark.height = hei;
        mark.originalWidth = this.imageView.clientWidth;
        mark.originalHeight = this.imageView.clientHeight;
        mark.cls = {
          position: "absolute",
          width: wid + "px",
          height: hei + "px",
          color: "white",
          left: bx + "px",
          top: by + "px"
        };

        this.markinfoscache[this.selectedIndex].push(mark);
        this.$emit(
          "updateMarks",
          this.markinfoscache[this.selectedIndex],
          this.imagescache[this.selectedIndex].name
        );
        //this.maskObj.style.cursor = "default";
      }.bind(this);
    },

    handleDelMark(id) {
      var index = this.markinfoscache[this.selectedIndex].findIndex(function(
        val
      ) {
        return val.id === id;
      });
      console.log("index:-->" + index);
      if (index >= 0) {
        this.markinfoscache[this.selectedIndex].splice(index, 1);
      }

      this.$emit("updateMarks", this.markinfoscache);
    }
  }
};
</script>

<style>
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
  top: 2px;
  right: 2px;
  display: none;
}

.el-icon-delete:before {
  content: url("../../assets/images/backend/icon_delete_hover_press.png");
}

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
</style>
