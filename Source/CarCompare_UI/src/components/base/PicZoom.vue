<template>
  <div class="magnifier-box" :ref="id" @mousemove="mousemove" @mouseover="mouseover" @mouseleave="mouseleave">
    <img :src="url" alt="" :onerror="onerror">
    <div class="mouse-cover"></div>
  </div>
</template>

<script>
export default {
  props: {
    //图片放大倍数
    scale: {
      type: Number,
      default: 2.5
    },
    //图片路径
    url: {
      type: String,
      required: true
    },
    //大图路径
    bigUrl: {
      type: String,
      default: null
    },
    //放大时页面是否可滚动
    scroll: {
      type: Boolean,
      default: false
    },
    //是否可以使用放大
    enable: {
      type: Boolean,
      default: true
    },
    //图片获取失败显示图片
    onerror: {
      type: String,
      default: ""
    },
    //大图位置 left,right,top,bottom
    position: {
      type: String,
      default: "right"
    },
    //大图宽度
    bigimgwidth: {
      type: Number,
      default: 0
    },
    //大图高度
    bigimgheight: {
      type: Number,
      default: 0
    },
  },
  data() {
    return {
      id: null,
      cover: null,
      imgbox: null,
      imgwrap: null,
      img: null,
      canvas: null,
      ctx: null,
      rectTimesX: 0,
      rectTimesY: 0,
      imgTimesX: 0,
      imgTimesY: 0,
      init: false
    };
  },
  created() {
    var $chars =
      "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678"; /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
    var maxPos = $chars.length;
    var str = "";
    for (let i = 0; i < 10; i++) {
      str += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    this.id = str;
  },
  mounted() {
    this.$nextTick(() => {
      this.initBox();
    });
  },
  methods: {
    initBox() {
      let box = this.$refs[this.id];
      this.imgbox = box;
      this.cover = box.querySelector(".mouse-cover");
      this.cover.style.width = this.imgbox.offsetWidth / this.scale + "px";
      this.cover.style.height = this.imgbox.offsetHeight / this.scale + "px";
      this.cover.style.left = "-100%";
      this.cover.style.top = "-100%";
      this.imgwrap = box.querySelector("img");
      let imgsrc;
      let self = this;
      if (this.bigUrl) {
        imgsrc = this.bigUrl;
      } else {
        imgsrc = this.imgwrap.src;
      }
      this.img = new Image();
      this.img.src = imgsrc;
      this.img.onload = () => {
        self.cover.style.display = "block";
        (self.rectTimesX = self.cover.offsetWidth / self.imgwrap.offsetWidth),
          (self.rectTimesY =
            self.cover.offsetHeight / self.imgwrap.offsetHeight);
        (self.imgTimesX = self.img.width / self.imgwrap.offsetWidth),
          (self.imgTimesY = self.img.height / self.imgwrap.offsetHeight);
        self.cover.style.display = "none";
        self.init = true;
      };
      this.canvas = box.querySelector("bigimg");
      if (!this.canvas) {
        this.canvas = document.createElement("canvas");
        this.canvas.className = "mouse-cover-canvas";
        this.canvas.style.position = "absolute";
        this.canvas.style.left =
          this.imgbox.offsetLeft + this.imgbox.offsetWidth + 100 + "px";
        this.canvas.style.top = this.imgbox.offsetTop + "px";
        this.canvas.style.border = "1px solid #eee";
        this.canvas.style.zIndex = "99999";
        if(this.bigimgheight==0)
        {
            this.canvas.height = this.imgbox.offsetHeight;
        }
        else
        {
            this.canvas.height =this.bigimgheight;
        }
         if(this.bigimgwidth==0)
        {
            this.canvas.width = this.imgbox.offsetWidth;
        }
        else
        {
            this.canvas.width =this.bigimgwidth;
        }
       
        this.canvas.style.display = "none";
        document.body.appendChild(this.canvas);
      }
      this.ctx = this.canvas.getContext("2d");
    },
    changeUrl() {
      let imgsrc;
      if (this.bigUrl) {
        imgsrc = this.bigUrl;
      } else {
        imgsrc = this.url;
      }

      this.img.src = imgsrc;
    },
    mousemove(e) {
      if (!this.init || !this.enable) {
        return false;
      }
      let _this = this;

      //获取实际的offset
      // function offset(curEle) {
      //   var totalLeft = null,
      //     totalTop = null,
      //     par = curEle.offsetParent;
      //   //首先加自己本身的左偏移和上偏移
      //   totalLeft += curEle.offsetLeft;
      //   totalTop += curEle.offsetTop;
      //   //只要没有找到body，我们就把父级参照物的边框和偏移也进行累加
      //   while (par) {
      //     if (navigator.userAgent.indexOf("MSIE 8.0") === -1) {
      //       //累加父级参照物的边框
      //       totalLeft += par.clientLeft;
      //       totalTop += par.clientTop;
      //     }

      //     //累加父级参照物本身的偏移
      //     totalLeft += par.offsetLeft;
      //     totalTop += par.offsetTop;
      //     par = par.offsetParent;
      //   }

      //   return {
      //     left: totalLeft,
      //     top: totalTop
      //   };
      // }

      function getXY(eve) {
        return {
          x: eve.clientX - _this.cover.offsetWidth / 2,
          y: eve.clientY - _this.cover.offsetHeight / 2
        };
      }
      let oEvent = e || event;
      let pos = getXY(oEvent);
      //let imgwrap = offset(this.imgwrap);
      var imgwraprect = this.imgwrap.getBoundingClientRect();
      let scrollTop =
        document.documentElement.scrollTop ||
        window.pageYOffset ||
        document.body.scrollTop;
      let scrollLeft =
        document.documentElement.scrollLeft ||
        window.pageXOffset ||
        document.body.scrollLeft;
      let range = {
        // minX: imgwraprect.left - scrollLeft,
        // maxX:
        //   imgwraprect.left +
        //   this.imgwrap.offsetWidth -
        //   this.cover.offsetWidth -
        //   scrollLeft,
        // minY: imgwraprect.top - scrollTop,
        // maxY:
        //   imgwraprect.top +
        //   this.imgwrap.offsetHeight -
        //   this.cover.offsetHeight -
        //   scrollTop
        minX: imgwraprect.left,
        maxX:
          imgwraprect.left + this.imgwrap.offsetWidth - this.cover.offsetWidth,
        minY: imgwraprect.top,
        maxY:
          imgwraprect.top + this.imgwrap.offsetHeight - this.cover.offsetHeight
      };
      if (pos.x > range.maxX) {
        pos.x = range.maxX;
      }
      if (pos.x < range.minX) {
        pos.x = range.minX;
      }
      if (pos.y > range.maxY) {
        pos.y = range.maxY;
      }
      if (pos.y < range.minY) {
        pos.y = range.minY;
      }
      this.cover.style.left = pos.x + "px";
      this.cover.style.top = pos.y + "px";

      this.ctx.clearRect(
        0,
        0,
        this.imgwrap.offsetWidth,
        this.imgwrap.offsetHeight
      );
      // let startX = pos.x - (imgwraprect.left - scrollLeft),
      //   startY = pos.y - (imgwraprect.top - scrollTop);
      let startX = pos.x - imgwraprect.left,
        startY = pos.y - imgwraprect.top;

      var imgboxrect = this.imgbox.getBoundingClientRect();
      //  this.canvas.style.left =
      //    imgboxrect.left + this.imgbox.offsetWidth + 10 + scrollLeft + "px";
      if(this.position=="right") {
         this.canvas.style.left = imgboxrect.left + this.imgbox.offsetWidth + 10 + scrollLeft + "px";
         this.canvas.style.top = imgboxrect.top + scrollTop + "px";
      }
      else if(this.position=="left") {
         this.canvas.style.left = imgboxrect.left - this.canvas.offsetWidth - 10 + scrollLeft + "px";
         this.canvas.style.top = imgboxrect.top + scrollTop + "px";
      }
      else if(this.position=="top") {
         this.canvas.style.left = imgboxrect.left  + scrollLeft + "px";
         this.canvas.style.top = imgboxrect.top - this.canvas.offsetHeight - 10 + scrollTop + "px";
      }
      else if(this.position=="bottom") {
         this.canvas.style.left = imgboxrect.left  + scrollLeft + "px";
         this.canvas.style.top = imgboxrect.top + this.imgbox.offsetHeight +10 + scrollTop + "px";
      }
      this.ctx.drawImage(
        this.img,
        startX * this.imgTimesX,
        startY * this.imgTimesY,
        this.img.width * this.rectTimesX,
        this.img.height * this.rectTimesY,
        0,
        0,
        this.imgbox.offsetWidth,
        this.imgbox.offsetHeight
      );
    },
    mouseover(e) {
      if (!this.init || !this.enable) {
        return false;
      }
      e = e || event;
      if (!this.scroll) {
        e.currentTarget.addEventListener(
          "mousewheel",
          function(ev) {
            ev.preventDefault();
          },
          false
        );

        e.currentTarget.addEventListener(
          "DOMMouseScroll",
          function(ev) {
            ev.preventDefault();
          },
          false
        );
      }

      this.cover.style.display = "block";
      this.canvas.style.display = "block";
    },
    mouseleave() {
      if (!this.init) {
        return false;
      }
      this.cover.style.display = "none";
      this.canvas.style.display = "none";
    }
  },
  watch: {
    url(val) {
      if (val != undefined) {
        this.changeUrl();
      }
    },
    bigUrl(val) {
      if (val != undefined) {
        this.changeUrl();
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.magnifier-box {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  position: relative;
  img {
    max-width: 100%;
    max-height: 100%;
  }
  .mouse-cover {
    position: fixed;
    background-color: rgba(0, 0, 0, 0.5);
    cursor: move;
  }
  .mouse-cover-canvas {
    position: fixed;
    left: 100%;
    top: 0;
    width: 100%;
    height: 100%;
  }
}
</style>

