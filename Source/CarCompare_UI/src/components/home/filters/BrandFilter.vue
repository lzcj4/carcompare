<template>
    <div class="container">
        <all-button class="all-button" ref="allButton" @selected="onSelectedAll" />
        <text-button v-for="group in source" :key="group.value" :ref="'letter_'+ group.value" class="icon-dropdown" :propGroupName="group.name" :propGroupValue="group.value" :propItemSource="group.itemSource" :propShowItemIcon="false" @selected="onSelectedItem" />
    </div>
</template>
<script>
import Bus from "@/components/common/Bus.vue";
import allButton from "@/components/home/filters/AllButton.vue";
import textButton from "@/components/home/TextButton/TextButton.vue";

export default {
  components: {
    allButton,
    textButton
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
      let lastRef = this.getLastRef();
      if (lastRef) {
        this.$refs[lastRef][0].unSelect();
        this.selectedItem = null;
      }

      Bus.$emit("filterClearSelected", this.getTagType());
    },
    onSelectedItem: function(args) {
      this.$refs.allButton.unSelect();

      let lastRef = this.getLastRef();
      let currentRef = "letter_" + args.group.value;

      if (lastRef && lastRef != currentRef) {
        this.$refs[lastRef][0].unSelect();
      }

      this.selectedItem = args;

      let tag = this.buildTag(args.group, args.item);
      Bus.$emit("filterItemSelected", tag);
    },
    buildTag: function(group, item) {
      return {
        type: this.getTagType(),
        name: "品牌:" + item.name,
        data: { group: group, item: item }
      };
    },
    getTagType: function() {
      return "BRAND";
    },
    getLastRef: function() {
      if (this.selectedItem) {
        return "letter_" + this.selectedItem.group.value;
      } else {
        return "";
      }
    },
    fillFilterParams: function(params) {      
      if (this.selectedItem) {
        params.brand = this.selectedItem.item.value;
      } else {
        params.brand = null;
      }
    }
  },
  mounted() {
    Bus.$on("filterBarTagRemoved", tag => {
      if (tag.type == this.getTagType()) {
        let lastRef = this.getLastRef();

        if (lastRef) {
          this.$refs[lastRef][0].unSelect();
          this.selectedItem = null;
        }

        this.$refs.allButton.select();
      }
    });
  }
};
</script>
<style scoped lang="scss">
.container{
    .all-button{
      float: left;
      margin-top: 3px;
    }

    .icon-dropdown {
      float: left;      
      margin-right: 5px;
  }
}
</style>

