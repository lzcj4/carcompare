<template>
    <div>
    <table class="table" v-show="tableVisible">
        <tr>
            <td class="label">品&emsp;&emsp;牌：</td>
            <td class="options brand-options">
                <brand-filter ref="brandFilter" :source="brandSource"/>
            </td>
        </tr>
        <tr>
            <td class="options" colspan="2">
                <logo-shape-filter class="logo-shape-filter" ref="logoShapeFilter" :source="logoShapeSource" />
                <year-filter class="year-filter" ref="yearFilter" :source="yearSource"/>
                <carriage-type-filter class="carriage-type-filter" ref="carriageTypeFilter" :source="carriageTypeSource" />												
            </td>
        </tr>	
        <tr>
            <td class="label">车&emsp;&emsp;型：</td>	
            <td class="options">                
                <car-type-filter ref="carTypeFilter" :source="carTypeSource"/>
            </td>
        </tr>	
        <tr>
            <td class="label">车标位置：</td>	
            <td class="options">
                <logo-placement-filter ref="logoPlacementFilter" :source="logoPlacementSource"/>
            </td>
        </tr>	
        <tr>
            <td class="label">车身前面：</td>	
            <td class="options">
                <front-feature-filter ref="frontFeatureFilter" :source="frontFeatureSource" />
            </td>
        </tr>	
        <tr>
            <td class="label">车身侧面：</td>	
            <td class="options">
                <side-feature-filter ref="sideFeatureFilter" :source="sideFeatureSource" />						
            </td>
        </tr>	
        <tr>
            <td class="label">车身后面：</td>	
            <td class="options">
                <back-feature-filter ref="backFeatureFilter" :source="backFeatureSource" />						
            </td>
        </tr>									
        <tr>
            <td class="label">车身顶面：</td>	
            <td class="options">
                <top-feature-filter ref="topFeatureFilter" :source="topFeatureSource" />									
            </td>
        </tr>

        <!-- <tr>
            <td colspan="2" style="background-color:#f8f8f8">
                <el-input
                type="textarea"
                autosize
                :rows="2"
                placeholder="过滤参数监视器"
                v-model="filterString">
                </el-input>                
            </td>
        </tr> -->
    </table>      
    </div>
</template>
<script>
import {getAllBrands, getAllFeatures,getVehicleYears} from '@/api'
import Bus from '@/components/common/Bus.vue'
import brandFilter from '@/components/home/filters/BrandFilter.vue'
import logoShapeFilter from '@/components/home/filters/LogoShapeFilter.vue'
import carriageTypeFilter from '@/components/home/filters/CarriageTypeFilter.vue'
import yearFilter from '@/components/home/filters/YearFilter.vue'
import carTypeFilter from '@/components/home/filters/CarTypeFilter.vue'
import logoPlacementFilter from '@/components/home/filters/LogoPlacementFilter.vue'
import frontFeatureFilter from '@/components/home/filters/FrontFeatureFilter.vue'
import sideFeatureFilter from '@/components/home/filters/SideFeatureFilter.vue'
import backFeatureFilter from '@/components/home/filters/BackFeatureFilter.vue'
import topFeatureFilter from '@/components/home/filters/TopFeatureFilter.vue'

export default {
    components: {
        brandFilter,
        logoShapeFilter,
        yearFilter,
        carriageTypeFilter,
        carTypeFilter,
        logoPlacementFilter,
        frontFeatureFilter,
        sideFeatureFilter,   
        backFeatureFilter,
        topFeatureFilter
    },
    data () {
        return {
            tableVisible: false,

            //品牌字典数据        
            brandSource: [],   
            
            //车标形框
            logoShapeSource: [],

            //年份
            yearSource: [],
            
            //厢型字典数据
            carriageTypeSource: [],

            //车型字典数据
            carTypeSource: [],

            //车标位置字典数据
            logoPlacementSource: [],

            //车身正面字典数据
            frontFeatureSource: [], 
            
            //车身侧面字典数据
            sideFeatureSource: [],

            //车身后面字典数据
            backFeatureSource: [],

            //车身顶部字典数据
            topFeatureSource: [],                      

            //过滤参数
            filterParams: {},

            filterString: ""
        }
    },
    methods: {
        updateParams: function(){
            this.$refs.brandFilter.fillFilterParams(this.filterParams);
            this.$refs.logoShapeFilter.fillFilterParams(this.filterParams);
            this.$refs.yearFilter.fillFilterParams(this.filterParams);
            this.$refs.carriageTypeFilter.fillFilterParams(this.filterParams);      
            this.$refs.carTypeFilter.fillFilterParams(this.filterParams); 
            this.$refs.logoPlacementFilter.fillFilterParams(this.filterParams);   
            this.$refs.frontFeatureFilter.fillFilterParams(this.filterParams);              
            this.$refs.sideFeatureFilter.fillFilterParams(this.filterParams);       
            this.$refs.backFeatureFilter.fillFilterParams(this.filterParams);       
            this.$refs.topFeatureFilter.fillFilterParams(this.filterParams);            
            this.filterString = JSON.stringify(this.filterParams);

            this.$emit('changed', this.filterParams);
        },
        initFilters: function(){
            this.initYearFilter();
            this.initBrandFilter();
            this.initFeatureFilter();   
            
            this.$nextTick(function () {
                this.tableVisible = true;
            })
        },
        initYearFilter: function(){
            const self = this; 
            
            self.yearSource.push({ name: "不限", value: "" });

            getVehicleYears({}).then(res=>{                
                if(res.success){                    
                    res.data.forEach(year=>{                        
                        self.yearSource.push({ name: year + "年", value: year });                       
                    });
                }
            });
        },
        initBrandFilter: function(){
            const self = this;     
            getAllBrands({}).then(res=>{            
                if(res.success){              
                    self.brandSource = res.data.brandSource; 
                }
            });
        },
        initFeatureFilter: function(){
            const self = this;     
            getAllFeatures({}).then(res=>{            
                if(res.success){   
                    self.logoShapeSource = res.data.logoShapeItems;
                    self.logoShapeSource.unshift({ name: "不限", value: "" });
                    
                    self.carriageTypeSource = res.data.carriageTypeItems;  
                    self.carriageTypeSource.unshift({ name: "不限", value: "" });

                    self.carTypeSource = res.data.carTypeItems;  
                    
                    self.logoPlacementSource = res.data.logoPlacementItems; 

                    //车身正面
                    self.frontFeatureSource.push({ name: "格栅结构", value: "geshanjiegou", showItemIcon: true, itemSource: res.data.geshanjiegouItems});
                    self.frontFeatureSource.push({ name: "上格栅形", value: "shanggeshanxing", showItemIcon: true, itemSource: res.data.shanggeshanxingItems});
                    self.frontFeatureSource.push({ name: "格栅前照", value: "geshanqianzhao", showItemIcon: true, itemSource: res.data.geshanqianzhaoItems});
                    self.frontFeatureSource.push({ name: "格栅前雾", value: "geshanqianwu", showItemIcon: true, itemSource: res.data.geshanqianwuItems});
                    self.frontFeatureSource.push({ name: "前包户板", value: "qianbaohuban", showItemIcon: false, itemSource: res.data.qianbaohubanItems});                    
                    self.frontFeatureSource.push({ name: "新能源标", value: "frontxinnengyuanbiao", showItemIcon: false, itemSource: res.data.frontxinnengyuanbiaoItems});  

                    //车身侧面
                    self.sideFeatureSource.push({ name: "B柱颜色", value: "bzhuyanse", showItemIcon: false, itemSource: res.data.bzhuyanseItems});
                    self.sideFeatureSource.push({ name: "后窗小窗", value: "houchuangxiaochuang", showItemIcon: true, itemSource: res.data.houchuangxiaochuangItems});
                    self.sideFeatureSource.push({ name: "后窗下沿", value: "houchuangxiayan", showItemIcon: true, itemSource: res.data.houchuangxiayanItems});
                    self.sideFeatureSource.push({ name: "门把手", value: "menbashou", showItemIcon: true, itemSource: res.data.menbashouItems});
                    self.sideFeatureSource.push({ name: "门把手颜色", value: "menbashouyanse", showItemIcon: false, itemSource: res.data.menbashouyanseItems});
                    self.sideFeatureSource.push({ name: "门线围", value: "menxianwei", showItemIcon: true, itemSource: res.data.menxianweiItems});                    
                    self.sideFeatureSource.push({ name: "油箱盖位", value: "youxianggaiwei", showItemIcon: false, itemSource: res.data.youxianggaiweiItems});
                    self.sideFeatureSource.push({ name: "轮毂轮辐", value: "lungulunfu", showItemIcon: true, itemSource: res.data.lungulunfuItems});
                    self.sideFeatureSource.push({ name: "腮饰", value: "saishi", showItemIcon: false, itemSource: res.data.saishiItems});
                    self.sideFeatureSource.push({ name: "侧窗框", value: "chechuangneikuang", showItemIcon: false, itemSource: res.data.chechuangneikuangItems});
                    //self.sideFeatureSource.push({ name: "新能源标", value: "sidexinnengyuanbiao", showItemIcon: false, itemSource: res.data.sidexinnengyuanbiaoItems});                                        

                    //车身后面
                    self.backFeatureSource.push({ name: "后窗框形", value: "houchuangkuangxing", showItemIcon: true, itemSource: res.data.houchuangkuangxingItems});
                    self.backFeatureSource.push({ name: "尾灯位置", value: "weidengweizhi", showItemIcon: true, itemSource: res.data.weidengweizhiItems});
                    self.backFeatureSource.push({ name: "尾灯形状", value: "weidengxingzhuang", showItemIcon: true, itemSource: res.data.weidengxingzhuangItems});
                    self.backFeatureSource.push({ name: "号牌框眉", value: "haopaikuangmei", showItemIcon: true, itemSource: res.data.haopaikuangmeiItems});
                    self.backFeatureSource.push({ name: "号牌框形", value: "haopaikuangxing", showItemIcon: true, itemSource: res.data.haopaikuangxingItems});
                    self.backFeatureSource.push({ name: "号牌位置", value: "haopaiweizhi", showItemIcon: true, itemSource: res.data.haopaiweizhiItems});
                    self.backFeatureSource.push({ name: "高位制动灯", value: "gaoweizhidongdeng", showItemIcon: true, itemSource: res.data.gaoweizhidongdengItems});
                    self.backFeatureSource.push({ name: "后防雾灯", value: "houfangwudeng", showItemIcon: true, itemSource: res.data.houfangwudengItems});
                    self.backFeatureSource.push({ name: "排气口位", value: "paiqikouwei", showItemIcon: true, itemSource: res.data.paiqikouweiItems});
                    self.backFeatureSource.push({ name: "新能源标", value: "backxinnengyuanbiao", showItemIcon: false, itemSource: res.data.backxinnengyuanbiaoItems});
                    self.backFeatureSource.push({ name: "外挂备胎", value: "waiguabeitai", showItemIcon: false, itemSource: res.data.waiguabeitaiItems});                    

                    //车身顶部
                    self.topFeatureSource.push({ name: "顶密封条", value: "dingmifengtiao", showItemIcon: true, itemSource: res.data.dingmifengtiaoItems});
                    self.topFeatureSource.push({ name: "尾翼扰流", value: "weiyiraoliu", showItemIcon: true, itemSource: res.data.weiyiraoliuItems});
                    self.topFeatureSource.push({ name: "行李支架", value: "xinglizhijia", showItemIcon: false, itemSource: res.data.xinglizhijiaItems});
                    self.topFeatureSource.push({ name: "背鳍天线", value: "beijitianxian", showItemIcon: true, itemSource: res.data.beijitianxianItems});
                                                          
                }
            });
        }
    },
    mounted() {
		Bus.$on('filterClearSelected',() => {                       
            this.updateParams();            
		})

		Bus.$on('filterItemSelected',(tag) => {                          
            this.updateParams();            
        })   
        
        Bus.$on('notifyUpdateFilterParams', (tag)=>{
            this.updateParams();            
        })   

        this.initFilters();
    }
}
</script>

<style scoped lang="scss">
.table{
    width: 900px;
    border-collapse: collapse;
    tr {        
        height: 34px;
    }
    .label{
        width: 68px;
        border-top:1px #f2f2f2 solid;
        border-bottom:1px #f2f2f2 solid;
        vertical-align: top;
        padding-top: 7px;
        font-size: 12px;
    }
    .options{
        width: auto;
        border-top:1px #f2f2f2 solid;
        border-bottom:1px #f2f2f2 solid;
        text-align: left;
    }
}
</style>
<style lang="scss">
.table{
    .options{
        .el-select{
            input{
                height: 24px !important;
            }
        }
    }
}
</style>


