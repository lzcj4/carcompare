<template>
    <a href="javascript:;" class="btn">     
        <span>上传本地图片</span>     
    </a>   
</template>
<script>
export default {
}
</script>
<style lang="scss" scoped>
    a{
        text-decoration: none;
    }

    a:hover{text-decoration: none;}     

    .btn{     
        display: inline-block;        
        padding: 6px 20px;     
        border-radius: 2px;     
        border: 1px #DDE9F3 solid;  
        color: #1B90F7;             
        cursor: pointer;             
    }     
    .btn:hover{     
        border: 1px #1B90F7 solid;   
    }     
        
    .btn{     
        font-style: normal;     
    }   

    .btn span{     
        display: block;
        line-height: 16px;
        margin-left: 2px;     
        padding-left: 25px;  
        background: url(./upload-arrow.png) left center no-repeat;            
    }  
</style>


