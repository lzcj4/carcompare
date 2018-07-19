<template>
    <span class="button" :class="isSelected ? 'button-actived':''" @click="onClick">不限</span>
</template>
<script>
export default {
    data () {
        return {
            selected: true  
        }
    },    
    methods: {
        onClick: function(){
            this.selected = true;
            this.$emit('selected', this.selected)
        },
        select: function(){
            this.selected = true;
        },
        unSelect: function(){
            this.selected = false;
        }
    },
    computed: {
        isSelected: function(){            
            return this.selected;
        }
    }
}
</script>
<style lang="scss" scoped>
.button{
    float: left;    
    margin-right: 15px;
    cursor: pointer;
    font-size: 12px;
}

.button:hover{
    font-weight: bold;
}

.button-actived{
    font-weight: bold;  
}
</style>


