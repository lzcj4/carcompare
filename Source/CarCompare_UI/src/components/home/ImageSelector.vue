<template>
  <div class="container">
    <div class="upload-box" @click="onUploadBoxClick">        
        <pic-zoom :url="imageSource" :enable="picZoomEnable" />
        <img class="magnifier" src="../../assets/images/frontend/magnifier.png" @click.stop="magnifierClickHandler"/>
    </div>
    <upload-button class="upload-button" @click.native="onUploadClick"/>
    <input type="file" id="uploadFile" accept="image/jpeg" @change="onUploadFileChanged" style="display:none;" />	      
  </div>
</template>
<script>
import picZoom from '@/components/base/PicZoom.vue'
import uploadButton from '@/components/home/UploadButton/UploadButton.vue'
import defaultContrast from "@/assets/images/frontend/upload-image-bg.png";

export default {
    components: {
        picZoom,
        uploadButton
    },
	data(){
		return {
			imageSource: defaultContrast,
            picZoomEnable:false
		}
	},
	methods: {		
		onUploadClick: function(){                
            this.doUploadClick();	
        },
        onUploadBoxClick: function(){  
            if(this.imageSource != defaultContrast){
                return;
            }                    
            this.doUploadClick();
        },
        doUploadClick: function(){
            this.$el.querySelector("#uploadFile").click();		
        },     
		onUploadFileChanged: function(e){
			const self = this;
			//创建一个FileReader对象  
			var reader = new FileReader();  
			//读取File对象的数据  
			reader.onload = function(evt){                  
				self.imageSource = evt.target.result;
			}  
            reader.readAsDataURL(e.target.files[0]); 
            this.picZoomEnable=true; 
        },
        magnifierClickHandler: function(){

        },
        getImageSource: function(){
            return this.imageSource;
        }
	},
	mounted(){

	},
	computed: {
		showPlus(){
            return this.imageSource == null || this.imageSource == "";
		},
		showImage(){
			return this.imageSource != null && this.imageSource != "";
		}
	}
}
</script>
<style lang="scss" scoped>
    .container{
        .upload-box{
            position: relative;
            width: 250px;
            height: 250px;
            line-height:250px;  
            border: 1px#DDE9F3 solid;  
            text-align:center;                                    
            .plus{
                vertical-align: middle;                   
            }
            .magnifier{
                position: absolute;  
                bottom: 5px;
                right: 5px;
                display: none; /*该图标暂且隐藏*/
            }
            .image{                        
                position: absolute; 
                top: 0px;
                left: 0px;                       
                width: 250px;
                height: 250px;
            }                    
        }
        .upload-button{                         
            margin-left: 54px;           
            margin-top: 15px;            
        }
    }
</style>