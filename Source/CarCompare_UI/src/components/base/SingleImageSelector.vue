<template>
  <div class="img-upload">
    <div v-show="imageUrl != null && imageUrl.url != ''">
      <div :class="[this.$props.selectedIndex==this.$props.index?'img-selected':'' ,'img-containner']">
        <img :src="imageUrl.url" @click="handleSelect" :onerror='"this.src=\"" + require("../../assets/images/default.png") + "\""' 
        @dblclick="handleDBClick" v-image-resize="imageSet" />
        <i @click="handleRemove" class="el-icon-delete img-btn-delete"></i>
      </div>
      <p class="img-title" v-text="imageUrl.name"></p>
    </div>
    <el-upload ref="dd" v-show="imageUrl == null || imageUrl.url == ''" class="img-upload" action="/api/files" @click="" :auto-upload=false :on-change="handleSelected" list-type="picture-card" :show-file-list="false" :objectBind="index" accept="image/png, image/jpeg" :on-remove="handleRemove">
      <i class="el-icon-plus"></i>
    </el-upload>
  </div>
</template>

<script>
export default {
  props: ["imageUrl", "index", "selectedIndex"],
  data: function() {
    return {
      image: this.$props.imageUrl,
      prevent: false,
      timer: null,
      delay: 200,
      imageSet:{width:96,height:72}
    };
  },
  methods: {
    handleSelected(file) {
      if (file != null) {
        var tmpFile = {};
        tmpFile.name = file.name;
        tmpFile.url = file.url;
        this.image = tmpFile;
        tmpFile = null;
        this.$emit("add", file, this.$props.index, this.image);
      }
    },
    handleRemove(callback) {
      if (this.$props.imageUrl != null) {
        this.$emit(
          "deleted",
          this.$props.imageUrl,
          this.$props.index,
          callback
        );
      }
    },
    handleDBClick() {
      clearTimeout(this.timer);
      this.prevent = true;
      console.log("handleDBClick");
      this.handleRemove(this.$refs.dd.$children[0].handleClick);
    },
    handleSelect() {
      this.timer = setTimeout(
        function() {
          console.log("setTimeout");
          if (!this.prevent) {
            console.log("handleSelect");
            if (this.$props.imageUrl != null) {
              this.$emit("selected", this.$props.imageUrl, this.$props.index);
            }           
          }
          this.prevent = false;
        }.bind(this),
        this.delay
      );
    }
  }
};
</script>

<style>
.img-upload .el-upload--picture-card {
  width: 98px;
  height: 74px;
  border: 1px solid #e5e7f7;
  border-radius: 0px;
}

.img-containner {
  width: 96px;
  height: 72px;
  display: table-cell;
  vertical-align: middle;
  border:1px #e6e6e6 solid;
  text-align: center; 
  position: relative;
  line-height: 0%;
}
.img-containner:hover .img-btn-delete {
  display: inline-block;
  height: 1em;
  width: 1em;
}

.el-upload--picture-card {
  line-height: 74px;
}

.img-selected {
  border: 1px solid #1b90f7;
}
.img-btn-delete {
  position: absolute;
  bottom: 2px;
  right: 2px;
  display: none;
}

.img-title {
  line-height: 15px;
  font-family: "MicrosoftYahei";
  font-size: 12px;
  color: #062238;
  text-align: left;
  width: 100%;
  margin: 5px auto auto auto;
  white-space: normal;
  word-wrap: break-word;
  padding: 0 0 0 0;
  /*! autoprefixer: off */
  -webkit-box-orient: vertical;
  /* autoprefixer: on */
  -webkit-line-clamp:2;
  overflow:hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
}
</style>
