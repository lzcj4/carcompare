<template>
    <div class="container">        
        <all-button class="all-button" ref="allButton" @selected="onSelectedAll" />
        <div class="box" :class="isSelectedItem(item) ? 'box-selected' : ''" v-for="item in source" @click="onSelectItem(item)">
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
      selectedItem: null
    };
  },
  methods: {
    onSelectedAll: function() {
      this.selectedItem = null;
      Bus.$emit("filterClearSelected", this.getTagType());
    },
    getIcon: function(item) {
      return require("../../../assets/images/frontend/carType/" +
        item.icon +
        ".png");
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
        name: "车型:" + item.name,
        data: { item: item }
      };
    },
    getTagType: function() {
      return "CAR_TYPE";
    },
    fillFilterParams: function(params) {
      if (this.selectedItem) {
        params.type = this.selectedItem.value;
      } else {
        params.type = "";
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


