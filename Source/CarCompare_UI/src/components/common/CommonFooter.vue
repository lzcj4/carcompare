<template>
    <div class="footer">
        <div class="left">&nbsp;</div>
        <div class="center" v-html="GLOBAL.settings.common.copyright"></div>
        <div class="right"><span v-html="GLOBAL.settings.common.picStatement"></span></div>  
    </div>
</template>
<script>
export default {  
}
</script>
<style lang="scss" scoped>
.footer {
    padding: 0px;
    width: 100%;
    height: 40px;        
    line-height: 40px;
    background: #232323;
    position: absolute;  
    bottom: 0px;

    .left{
        width:30%;
        min-width: 50px;
        float: left;
    }

    .center{    
        width:40%;  
        float: left;
        text-align: center;
        color: #f2f2f2;
    }

    .right{
        width:30%; 
        float: left;
        color: #a2a2a2;
        text-align: right;
        
        span{
            display: inline-block;
            font-size: 12px; 
            -webkit-transform: scale(0.5,0.5);
        }
    }
}
</style>

