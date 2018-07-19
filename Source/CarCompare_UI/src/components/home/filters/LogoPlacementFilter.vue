<template>
    <div class="container">
        <all-button class="all-button" ref="allButton" @selected="onSelectedAll" />

        <div class="box" :class="isSelectedItem(item) ? 'box-selected' : ''" v-for="item in source" @click="onSelectItem(item)" @mouseover="onMouseOver(item)" @mouseout="onMouseOut(item)">
            <div><img :src="getIcon(item)" /></div>
            <span>{{item.name}}</span>
        </div>
    </div>
</template>
<script>
import Bus from "@/components/common/Bus.vue";
import allButton from "@/components/home/filters/AllButton.vue";

export default {
  components: {
    allButton
  },
  props: {
    source: {
      default: function() {
        return [];
      }
    }
  },
  data() {
    return {      
      selectedItem: null,
      hoveredItem: null
    };
  },
  methods: {
    onMouseOver: function(item){
      this.hoveredItem = item;
    },
    onMouseOut: function(item){
      this.hoveredItem = null;
    },    
    onSelectedAll: function() {
      this.selectedItem = null;
      Bus.$emit("filterClearSelected", this.getTagType());
    },
    getIcon: function(item) {
      if (this.selectedItem == item || this.hoveredItem == item) {
        return require("../../../assets/images/frontend/logo-placement/" +
          item.icon +
          "_actived.png");
      } else {
        return require("../../../assets/images/frontend/logo-placement/" +
          item.icon +
          "_normal.png");
      }
    },
    isSelectedItem: function(item) {
      return this.selectedItem == item;
    },
    onSelectItem: function(item) {
      this.selectedItem = item;

      this.$refs.allButton.unSelect();

      let tag = this.buildTag(item);
      Bus.$emit("filterItemSelected", tag);
    },
    unSelect: function() {
      this.selectedItem = null;
    },
    buildTag: function(item) {
      return {
        type: this.getTagType(),
        name: "车标位置:" + item.name,
        data: { item: item }
      };
    },
    getTagType: function() {
      return "LOGO_PLACEMENT";
    },
    fillFilterParams: function(params) {
      if (this.selectedItem) {
        params.chebiaoweizhi = this.selectedItem.value;
      } else {
        params.chebiaoweizhi = "";
      }
    }
  },
  mounted() {
    Bus.$on("filterBarTagRemoved", tag => {      
      if (tag.type == this.getTagType()) {
        if (this.selectedItem) {
          this.unSelect();
          this.selectedItem = null;
        }

        this.$refs.allButton.select();
      }
    });
  }
};
</script>
<style lang="scss" scoped>
@import "../../../assets/styles/vars.scss";

.container{
  .all-button{
    margin-top: 7px;
  }

  .box {
    float: left;
    line-height: 15px;
    text-align: center;
    margin: 0px 5px;
    padding: 3px 0px;
    cursor: pointer;
    div {
      width: 50px;
      height: 22px;
      display: table-cell;
      vertical-align: middle;
      text-align: center;
    }
    span {
      text-align: center;
      vertical-align: middle;
      padding: 3px 6px;
      border-radius: 2px;
      font-size: 12px;
    }
}

.box:hover {
  span {
    color: #fff;
    background-color: $color-primary;
  }
}

.box-selected {
  span {
    color: #fff;
    background-color: $color-primary;
  }
}
}
</style>


