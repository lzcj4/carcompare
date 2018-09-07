<template >
  <div class="feature-containner  feature-view">
    <div class="feature-header">
      <span>车型特征</span>
      <!--
      <el-button v-if="feature.id == ''" class="btn-reset" size="small" type="primary" @click="handleReset">重置</el-button>
      -->
    </div>
    <el-collapse ref="featureDiv" v-model="activeNames" class="feature-selector-inner-disable">
      <div class="feature-hotindex">
        <span>热门指数：</span>
        <el-rate v-model="feature.hotindex" :disabled="!canEditFeature"></el-rate>
      </div>
      <el-collapse-item name="1" title="品牌类型">
        <div class="feature-item">
          <span>品牌：</span>
          <el-select v-model="feature.brand" placeholder="请选择" class="feature-selector-inner" id="brandSelector" name="brandSelector" v-on:change="handleChange('brand')" v-bind:class="{'error':rules.brand}" filterable>
            <div v-for="(item ,index) in this.featureSource.brandItems" :key="index">
              <el-option v-for="(item ,index) in item.itemSource" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
              </el-option>
            </div>
          </el-select>
        </div>
        <div class="feature-item">
          <span>车系：</span>
          <el-select v-model="feature.series" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange('series')" v-bind:class="{'error':rules.series}">
            <el-option v-for="(item ,index) in this.featureSource.seriesItems" :key="index" :label="item.value" :value="item.key" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>年款：</span>
          <el-select v-model="feature.modelyear" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange('modelyear')" v-bind:class="{'error':rules.modelyear}">
            <el-option v-for="(item ,index) in this.featureSource.yearItems" :key="index" :label="item.value" :value="item.key" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>配置型：</span>
          <el-select v-model="feature.model" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange('model')" v-bind:class="{'error':rules.model}">
            <el-option v-for="(item ,index) in this.featureSource.modelItems" :key="index" :label="item.value" :value="item.key" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>车型：</span>
          <el-select v-model="feature.type" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange('type')" v-bind:class="{'error':rules.type}">
            <el-option v-for="(item ,index) in this.featureSource.carTypeItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>厢型：</span>
          <el-select v-model="feature.xiangxing" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.carriageTypeItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>车标框形：</span>
          <el-select v-model="feature.chebiaokuangxing" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.logoShapeItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>车标位置：</span>
          <el-select v-model="feature.chebiaoweizhi" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.logoPlacementItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
      </el-collapse-item>
      <el-collapse-item name="2" title="车身前面">
        <div class="feature-item">
          <span>格栅结构：</span>
          <el-select v-model="feature.geshanjiegou" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.geshanjiegouItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>上格栅形：</span>
          <el-select v-model="feature.shanggeshanxing" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.shanggeshanxingItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>格栅前雾：</span>
          <el-select v-model="feature.geshanqianwu" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.geshanqianwuItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>格栅前照：</span>
          <el-select v-model="feature.geshanqianzhao" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.geshanqianzhaoItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>前包护板：</span>
          <el-select v-model="feature.qianbaohuban" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.qianbaohubanItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>新能源标：</span>
          <el-select v-model="feature.frontxinnengyuanbiao" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.frontxinnengyuanbiaoItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
      </el-collapse-item>
      <el-collapse-item name="3" title="车身侧面">
        <div class="feature-item">
          <span>B柱颜色：</span>
          <el-select v-model="feature.bzhuyanse" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.bzhuyanseItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>后窗小窗：</span>
          <el-select v-model="feature.houchuangxiaochuang" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.houchuangxiaochuangItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>后窗下沿：</span>
          <el-select v-model="feature.houchuangxiayan" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.houchuangxiayanItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>门把手：</span>
          <el-select v-model="feature.menbashou" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.menbashouItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>门把手颜色：</span>
          <el-select v-model="feature.menbashouyanse" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.menbashouyanseItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>门线圈：</span>
          <el-select v-model="feature.menxianwei" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.menxianweiItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>油箱盖位：</span>
          <el-select v-model="feature.youxianggaiwei" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.youxianggaiweiItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <!--
         <div class="feature-item">
          <span>新能源标：</span>
          <el-select v-model="feature.sidexinnengyuanbiao" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.sidexinnengyuanbiaoItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        -->
        <div class="feature-item">
          <span>轮毂轮辐：</span>
          <el-select v-model="feature.lungulunfu" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.lungulunfuItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>腮饰：</span>
          <el-select v-model="feature.saishi" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange" >
            <el-option v-for="(item ,index) in this.featureSource.saishiItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>侧窗框：</span>
          <el-select v-model="feature.chechuangneikuang" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange" >
            <el-option v-for="(item ,index) in this.featureSource.chechuangneikuangItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
      </el-collapse-item>
      <el-collapse-item name="4" title="车身后面">
        <div class="feature-item">
          <span>后窗框形：</span>
          <el-select v-model="feature.houchuangkuangxing" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.houchuangkuangxingItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>尾灯位置：</span>
          <el-select v-model="feature.weidengweizhi" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.weidengweizhiItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>尾灯形状：</span>
          <el-select v-model="feature.weidengxingzhuang" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.weidengxingzhuangItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>号牌框眉：</span>
          <el-select v-model="feature.haopaikuangmei" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.haopaikuangmeiItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>号牌框形：</span>
          <el-select v-model="feature.haopaikuangxing" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.haopaikuangxingItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>号牌位置：</span>
          <el-select v-model="feature.haopaiweizhi" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.haopaiweizhiItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>高位制动灯：</span>
          <el-select v-model="feature.gaoweizhidongdeng" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.gaoweizhidongdengItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>后防雾灯：</span>
          <el-select v-model="feature.houfangwudeng" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.houfangwudengItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>排气口位：</span>
          <el-select v-model="feature.paiqikouwei" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.paiqikouweiItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>外挂备胎：</span>
          <el-select v-model="feature.waiguabeitai" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.waiguabeitaiItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>新能源标：</span>
          <el-select v-model="feature.backxinnengyuanbiao" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.backxinnengyuanbiaoItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
      </el-collapse-item>
      <el-collapse-item ref="item5" name="5" title="车身顶部">
        <div class="feature-item">
          <span>顶密封条：</span>
          <el-select v-model="feature.dingmifengtiao" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.dingmifengtiaoItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>尾翼扰流：</span>
          <el-select v-model="feature.weiyiraoliu" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.weiyiraoliuItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>行李支架：</span>
          <el-select v-model="feature.xinglizhijia" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.xinglizhijiaItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
        <div class="feature-item">
          <span>背鯺天线：</span>
          <el-select v-model="feature.beijitianxian" placeholder="请选择" class="feature-selector-inner" v-on:change="handleChange">
            <el-option v-for="(item ,index) in this.featureSource.beijitianxianItems" :key="index" :label="item.name" :value="item.value" class="feature-selector-inner">
            </el-option>
          </el-select>
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
import {
  getAllBrands,
  getSeriesByBrandId,
  getModelYearsBySeriesId,
  getModelsByModelYearId,
  getAllFeatures
} from "@/api";

import { createEmptyFeatrues } from "../manage/car/models";

export default {
  name: "FeatureSelector",
  components: {},
  props: ["featuresInfo", "rules"],
  watch: {
    featuresInfo: {
      handler: function(val) {
        this.feature = val.features;
        this.initLevelDataSource();
        this.activeNames= ["1"];
      },
      deep: true
    }
  },
  data: function() {
    return {
      feature:
        this.$props.featuresInfo === undefined
          ? createEmptyFeatrues()
          : this.$props.featuresInfo.features,
      validates: { brand: true },
      featureSource: createEmptyFeatrues(),
      activeNames: ["1"],
      canEditFeature: false
    };
  },
  mounted() {
    getAllFeatures().then(res => {
      if (res.success) {
        this.featureSource = res.data;
      }
      getAllBrands().then(rs => {
        if (rs.success) {
          this.$set(this.featureSource, "brandItems", rs.data.brandSource);
        }
      });
    });

    this.initLevelDataSource();

    //保存完成清楚数据
    this.$root.$on(
      "car-saved-done",
      function() {
        console.log("car-saved-done");
        this.clearSeires();
        this.clearYear();
        this.clearModel();
      }.bind(this)
    );
  },
  methods: {
    handleChange(name) {},
    handleReset() {
      this.$emit("feature:reset");
    },
    clearModel() {
      this.$set(this.featureSource, "modelItems", []);
      this.feature.model = "";
    },
    clearYear() {
      this.clearModel();
      this.$set(this.featureSource, "yearItems", []);
      this.feature.modelyear = "";
    },
    clearSeires() {
      this.clearYear();
      this.$set(this.featureSource, "seriesItems", []);
      this.feature.series = "";
    },
    initLevelDataSource() {
      if (this.feature.id != "")
        if (this.feature.brand != "") {
          getSeriesByBrandId({ brandId: this.feature.brand }).then(res => {
            if (res.success) {
              console.log("watch handler featureSource");
              this.$set(this.featureSource, "seriesItems", res.data);
              if (this.feature.series != "") {
                getModelYearsBySeriesId({ seriesId: this.feature.series }).then(
                  res => {
                    if (res.success) {
                      this.$set(this.featureSource, "yearItems", res.data);
                      if (this.feature.modelyear != "") {
                        getModelsByModelYearId({
                          modelyearId: this.feature.modelyear
                        }).then(res => {
                          this.$set(this.featureSource, "modelItems", res.data);
                        });
                      }
                    }
                  }
                );
              }
            }
          });
        }
    }
  }
};
</script>

<style lang="scss" >
.feature-containner {
  position: relative;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  background: #f5f6fa;
}

.feature-view {
  .feature-hotindex {
    width: 100%;
    height: 48px;
    line-height: 48px;
    font-family: "MicrosoftYahei-Bold";
    font-size: 14px;
    text-align: left;
    display: block;
    background: #e5e7ec;
    border-bottom: 1px solid #ffffff;
  }

  .feature-hotindex > span {
    margin-left: 20px;
    display: inline-block;
  }

  .feature-hotindex > div[role="slider"] {
    display: inline-block;
    width: 50%;
  }

  .el-rate__item {
    width: 16%;
    vertical-align: middle;
    line-height: 100%;
  }

  .el-collapse {
    overflow-y: auto;
    height: calc(100% - 46px) !important;
    border-bottom: 0px;
  }

  .el-collapse-item__arrow {
    float: left;
  }
  .feature-header {
    width: 100%;
    height: 30px;
    line-height: 30px;
    background: #273b4c;
    font-family: "MicrosoftYahei-Bold";
    font-size: 14px;
    color: #ffffff;
    text-align: center;
    display: block;
  }
  .feature-header sapn {
    margin: auto auto;
    text-align: center;
    display: block;
  }
  .el-collapse-item__header {
    background: #e5e7ec;

    border-bottom: 1px solid #ffffff;
  }
  .btn-reset {
    border-radius: 2px;
    background: #1b90f7;
    width: 60px;
    height: 22px;
    padding: 0;
    float: right;
    right: 10px;
    margin: 4px 10px;
  }
  .btn-reset span {
    padding: 20px auto;
    margin: 10px auto;
  }
  .btn-reset:hover {
    background: #5ab1fd;
  }

  .feature-item {
    position: relative;
    margin-top: 10px;
    margin-left: 6%;
  }

  .feature-item > span {
    display: inline-block;
    width: 86px;
  }

  .feature-selector-inner-disable .feature-selector-inner {
    pointer-events: none;
  }

  .feature-selector-inner {
    line-height: 100%;
    overflow: visible;
  }
  .feature-selector-inner input {
    width: 100%;
  }
  .feature-selector-inner span {
    line-height: 100%;
    overflow: visible;
  }
  .feature-selector-inner span i {
    display: none;
  }

  .error {
    border: 1px solid red;
    border-radius: 2px;
  }

  .el-popper[x-placement^="bottom"] {
    margin-top: 4px;
  }

  div[role="tablist"] {
    height: 100%;
    overflow-y: auto;
    border: 0px;
  }
}
</style>
