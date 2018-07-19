<template>
    <div class="dropdown" @mouseover="onDropdownMouseOver" @mouseout="onDropdownMouseOut">
        <div class="group" :class="isGroupActive() ? 'group-actived' : ''">{{propGroupName}}<i class="arrow"></i></div>  
        <div class="popup" v-show="popupVisible" >
          <ul>
              <li v-for="item in propItemSource" @click="onItemClick(item)" :class="isItemSelected(item) ? 'selected':''" @mouseover="onItemMouseOver(item)" @mouseout="onItemMouseOut(item)">
                  <div v-if="propShowItemIcon"><img :src="getIcon(item)"/></div>
                  <span>{{item.name}}</span>
              </li>
          </ul>
        </div>
    </div>    
</template>
<script>
export default {
  props: {
      propGroupName: {
          default : ""
      },
      propGroupValue: {
          default: ""
      },      
      propShowItemIcon: {
          default: false
      },
      propItemSource: {
          default: function(){
              return [];
          }
      },
      propSelectedItem: null
  },
  data () {
      return {
          popupVisible: false,
          hoveredItem: null,
          selectedItem: this.propSelectedItem,
          dropdownHovering: false          
      }
  },
  methods: {
    getIcon: function(item){
        if(this.hoveredItem == item || this.selectedItem == item){
            return require("../../../assets/images/frontend/"+this.propGroupValue+"/" + item.icon + "_actived.png");   
        }else{
            return require("../../../assets/images/frontend/"+this.propGroupValue+"/" + item.icon + "_normal.png");
        }        
    },   
    onDropdownMouseOver: function(){
        this.popupVisible = true;
        this.dropdownHovering = true;                
    },
    onDropdownMouseOut: function(){
        this.popupVisible = false;  
        this.dropdownHovering = false;    
    },    
    onItemClick: function(item){
        this.selectedItem = item;
        this.$emit("selected", { group: { value: this.propGroupValue, name: this.propGroupName }, item: item });
    },
    onItemMouseOver: function(item){
        this.hoveredItem = item;
    },
    onItemMouseOut: function(item){
        this.hoveredItem = null;
    },
    isGroupActive: function(){        
        return this.selectedItem != null || this.dropdownHovering;
    },
    isItemHovered: function(item){
        return this.selectedItem == item;
    },
    isItemSelected: function(item){
        return this.selectedItem == item;
    },
    unSelect: function(){    
        this.selectedItem = null;
    }
  }
}
</script>
<style lang="scss" scoped>
@import '../../../assets/styles/vars.scss';

.dropdown{ 
    padding: 0px;  
    div.group{        
        text-align: center;
        cursor: pointer;

        text-align: center;
        vertical-align: middle;
        padding: 3px 6px;
        border-radius: 2px;  
        color: #000;
        background-color: white;            
        // i.arrow{
        //     width: 20px;
        //     height: 20px;
        //     background-image: url('../../../assets/images/frontend/arrow_up_normal.png') no-repeat;
        // }
    }

    div.group-actived{
        color: #fff;
        background-color: $color_primary;
        // i.arrow{
        //     width: 20px;
        //     height: 20px;
        //     background-image: url('../../../assets/images/frontend/arrow_up_normal.png') no-repeat;
        // }
    }



    div.popup{
        z-index: 9999;
        position: absolute;  
        left: 10px;  
        width: 97%;            
        border: 1px #DAE4ED solid;
        background-color: #EBF5FF;
        padding: 8px 5px;
        border-radius: 2px;
        ul{
            margin: 0px;
            padding: 0px;
            list-style-type: none;
            li{            
                float: left;
                text-align: center;
                margin: 0px 10px;              
                line-height: 26px;
                cursor: pointer;            
                div{
                
                }
                span{             
                }            
            }
            li:hover{
                span{
                    color: $color_primary
                }
            }
            li.selected{
                span{
                    color: $color_primary
                }
            }
        }
    }
}
</style>


