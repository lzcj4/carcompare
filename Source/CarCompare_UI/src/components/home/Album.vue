<template>
  <div ref="body" :style="{display:onVisiable?'block':'none'}" class="albumContainer">
    <i class="close-btn" @click="close" />
    <div class="changeContainer" @click="goPreview">
      <div class="changeImage" >
        <i class="preview-lft"  />
      </div>
    </div>
    <div class="changeContainer changeRight " @click="goNext">
      <div class="changeImage">
        <i class="next-rgt"  />
      </div>
    </div>
    <div class="imageViewContainer">
      <img ref="imgView" :src="imgList.length >0?imgList[currentIndex]:''" :onerror='"this.src=\"" + require("../../assets/images/default.png") + "\""' />
    </div>
  </div>
</template>
<script>
export default {
  name: "Album",
  props: {
    visiable: {
      type: Boolean,
      default: false
    },
    imgList: {
      type: Array,
      default: [""]
    },
    index: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      currentIndex: 0,
      imageSize: { width: 0, height: 0 }
    };
  },
  mounted() {
    this.$refs.imgView.onload = e => {
      this.imageSize.width = e.target.naturalWidth;
      this.imageSize.height = e.target.naturalHeight;
      this.adjust();
    };

    window.onresize = e => {
      this.adjust();
    };
  },
  watch: {
    index(val) {
      this.currentIndex = val;
    }
  },
  methods: {
    goPreview() {
      if (this.$props.imgList.length <= 0) return;
      this.currentIndex--;
      if (this.currentIndex < 0)
        this.currentIndex = this.$props.imgList.length - 1;
    },
    goNext() {
      if (this.$props.imgList.length <= 0) return;
      this.currentIndex++;
      if (this.currentIndex >= this.imgList.length) this.currentIndex = 0;
    },
    close() {
      this.$emit("albumClosed");
    },
    adjust() {
      let w = window.innerWidth;
      let h = window.innerHeight;
      let newW = 0;
      let newH = 0;
      if (this.imageSize.width > w) {
        newW = w;
        newH = this.imageSize.height * w / this.imageSize.width;
        if (newH > h) {
          newW = w * h / newH;
          newH = h;
        }
      } else if (this.imageSize.height > h) {
        newW = this.imageSize.width * h / this.imageSize.height;
        newH = h;
        if (newW > w) {
          newH = h * w / newH;
          newW = w;
        }
      }else{
        newW = this.imageSize.width;
        newH = this.imageSize.height;
      }
      if (newW != 0) {
        this.$refs.imgView.width = newW;
        this.$refs.imgView.height = newH;
      }
    }
  },
  computed: {
    onVisiable() {
      this.currentIndex = this.$props.index;
      return this.$props.visiable;
    }
  }
};
</script>
<style>
.albumContainer {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  overflow: hidden;
  background: #0e1113;
  text-align: center;
  vertical-align: middle;
  z-index: 5001;
}
.changeContainer {
  position: relative;
  float: left;
  height: 100%;
  width: 20%;
  display: block;
  z-index: 5005;
}
.changeRight {
  float: right;
  right: 0;
}
.imageViewContainer {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  display: inline-flex;
  overflow: hidden;
}
.imageViewContainer img {
  margin: auto auto;
}
.changeImage {
  position: relative;
  top: 50%;
  left: 50%;
  height: 42px;
  width: 24px;
  display: none;
  transform: translateX(-12px) translateY(-21px);
}
.changeContainer:hover .changeImage {
  display: block;
}

.albumContainer > i {
  position: absolute;
  top: 20px;
  right: 20px;
  height: 20px;
  width: 20px;
  background: #333;
  z-index: 5006;
}

.preview-lft:before {
  content: url("../../assets/images/frontend/left_nor.png");
}

.preview-lft:hover::before {
  content: url("../../assets/images/frontend/left_hover_press.png");
}
.close-btn:before {
  content: url("../../assets/images/frontend/icon_suoxiao_nor.png");
}

.close-btn:hover::before {
  content: url("../../assets/images/frontend/icon_suoxiao_hover_press.png");
}
.next-rgt:before {
  content: url("../../assets/images/frontend/right_nor.png");
}

.next-rgt:hover::before {
  content: url("../../assets/images/frontend/right_hover_press.png");
}
</style>
