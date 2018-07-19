<template>
    <div class="dropdown" @mouseover="onDropdownMouseOver" @mouseout="onDropdownMouseOut">
        <div class="group" :class="isGroupActive() ? 'group-actived' : ''">
            <a href="javascript:;">     
                <span>{{propGroupName}}</span>     
            </a>         
        </div>          
        <div class="popup" :class="{'popup-noicon': !propShowItemIcon}" v-show="popupVisible" >
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
        cursor: pointer;
        
        a{
            text-decoration: none;

            display: inline-block;                
            padding: 4px 7px; 
            padding-top:3px;    
            border-radius: 3px;     
            background-color: #fff;     
            color: #000;     
            cursor: pointer; 
            font-style: normal;   
            font-size: 12px;  

            span{        
                padding-right: 12px;     
                background: url(./images/arrow_down_grey.png) right center no-repeat;     
            }   

            span:hover{
                background: url(./images/arrow_up_white.png) right center no-repeat;  
            }                       
        }        

        a:hover{
            text-decoration: none;
            color: #fff;        
            background-color: $color_primary;          
        }                                                     
    }

    div.group-actived{
        a{
            color: #fff;        
            background-color: $color_primary;  

            span{
                background: url(./images/arrow_up_white.png) right center no-repeat;  
            }    
        }
    }



    div.popup{
        z-index: 9999;
        position: absolute;  
        left: 10px;  
        width: 97%;            
        border: 1px #DAE4ED solid;
        background-color: #EBF5FF;
        padding: 5px;
        border-radius: 2px;
        ul{
            margin: 0px;
            padding: 0px;
            list-style-type: none;
            li{            
                float: left;
                text-align: center;
                margin: 0px 10px;              
                //width: 60px;  
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

    div.popup-noicon{
        padding: 8px 5px;
        ul{
            li{
                line-height: 26px;
            }
        }
    }
}
</style>