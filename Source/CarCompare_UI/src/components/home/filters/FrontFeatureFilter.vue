<template>
    <div class="container">
        <all-button class="all-button" ref="allButton" @selected="onSelectedAll" />
        
        <div class="dropdown-list">
            <text-arrow-button
                v-for="group in source"
                :ref="group.value" 
                :key="group.value"
                class="icon-dropdown"
                @selected="onSelectedItem" 
                :propGroupName="group.name" 
                :propGroupValue="group.value" 
                :propItemSource="group.itemSource" 
                :propShowItemIcon="group.showItemIcon" 
            />            
        </div>
    </div>
</template>
<script>
import Bus from "@/components/common/Bus.vue";
import allButton from '@/components/home/filters/AllButton.vue'
import textArrowButton from '@/components/home/TextArrowButton/TextArrowButton.vue'

export default {
    components: {
        allButton,
        textArrowButton
    },
    props: {
        source: {
            default: function(){
                return [];
            }
        }
    },
    data () {
        return {                        
            selectedItems: []
        }
    },
    methods: {
        onSelectedAll: function(){
            this.selectedItems.forEach(p=>{
                this.$refs[p.group.value][0].unSelect();
            });

            this.selectedItems.splice(0, this.selectedItems.length);

            let groupCodes = [];
            this.source.forEach(group=>{                
                groupCodes.push(group.value);
            });
            
            Bus.$emit("filterClearSelected", groupCodes);
        },
        onSelectedItem: function(args){
            this.$refs.allButton.unSelect();            
            
            var selectedItem = this.selectedItems.find(p=>p.group.value == args.group.value);
            if(selectedItem){
                this.selectedItems.splice(this.selectedItems.indexOf(selectedItem), 1);
            }
            
            this.selectedItems.push(args);

            let tag = this.buildTag(args.group, args.item);
            
            Bus.$emit("filterItemSelected", tag);
        },
        unSelect: function() {
            this.selectedItems.forEach(p=>{
                this.$refs[p.group.value][0].unSelect();
            });

            this.selectedItems.splice(0, this.selectedItems.length);
        },
        buildTag: function(group, item) {
            return {
                type: this.getTagType(group),
                name: group.name + ":" + item.name,
                data: { item: item }
            };
        },
        getTagType: function(group) {
            return group.value;
        },
        fillFilterParams: function(params) { 
            let _this = this;                               
            this.source.forEach(group=>{                          
                let selItem = _this.selectedItems.find(x=>x.group.value == group.value);
                if(selItem){
                    params[group.value] = selItem.item.value;
                }else{
                    params[group.value] = "";
                }                             
            });
        }        
    },
    mounted() {
        Bus.$on("filterBarTagRemoved", tag => {
            var selectedItem = this.selectedItems.find(p=>p.group.value == tag.type);
            if(selectedItem){
                this.$refs[selectedItem.group.value][0].unSelect();
                this.selectedItems.splice(this.selectedItems.indexOf(selectedItem), 1);
            }

            if(this.selectedItems.length == 0){
                this.$refs.allButton.select();         
            }
        });
    }    
}
</script>
<style scoped lang="scss">
.container{
    padding-top: 2px;

    .all-button{
        margin-top:3px;
    }

    .dropdown-list{            
        display: table;
        .icon-dropdown{
            float: left;
            margin-bottom: 3px;
            margin-right: 15px;
        }
    }
}
</style>