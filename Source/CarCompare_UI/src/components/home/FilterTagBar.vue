<template>
  <div class="filter-bar-container">
    <div class="tags">
      <span>已选条件：</span>
      <el-tag :key="tag.type" size="small" v-for="tag in tags" closable :disable-transitions="false" @close="handleClose(tag)">
        {{tag.name}}
      </el-tag>
      <el-button type="primary" size="mini" icon="el-icon-delete" @click="handleClear" class="button-clear" v-show="hasTag" >清空</el-button>      
    </div>    
  </div>
</template>
<script>
import Bus from "@/components/common/Bus.vue";

/**
 * tag结构: { type, name, data}
 */

export default {
  data() {
    return {
      tags: []
    };
  },
  computed: {
    hasTag: function() {
      return this.tags.length > 0;
    }
  },
  methods: {
    handleClose(tag) {
      this.tags.splice(this.tags.indexOf(tag), 1);
      Bus.$emit("filterBarTagRemoved", tag);

      Bus.$emit("notifyUpdateFilterParams");
    },

    addTag(newTag) {
      let tag = this.tags.find(t => t.type == newTag.type);
      if (tag) { //该类标签已存在，则更新
        tag.name = newTag.name;
      } else { //该类标签不存在，则新增
        this.tags.push(newTag);
      }
    },

    handleClear() {
      this.tags.forEach(tag => {
        Bus.$emit("filterBarTagRemoved", tag);
      });
      this.tags.splice(0, this.tags.length);
      
      Bus.$emit("notifyUpdateFilterParams");
    }
  },
  mounted() {
    Bus.$on("filterClearSelected", tagType => {
      if (typeof tagType === "string") {
        let tag = this.tags.find(t => t.type == tagType);
        this.tags.splice(this.tags.indexOf(tag), 1);
      } else if (typeof tagType === "object") {
        tagType.forEach(type => {
          let tag = this.tags.find(t => t.type == type);
          this.tags.splice(this.tags.indexOf(tag), 1);
        });
      }
    });

    Bus.$on("filterItemSelected", tag => {
      this.addTag(tag);
    });
  }
};
</script>
<style lang="scss" scoped>
.filter-bar-container {
  width: 100%;
  padding: 8px;
  .tags{  
    line-height: 26px;

    span:first-child{
      height: 24px;
      color: #666666;
    }
    .button-clear{
      height: 24px;
      padding: 0px 10px;
    }
  }
}
</style>
<style lang="scss">
.filter-bar-container{
  .el-tag + .el-tag {
    margin-left: 10px;
  }

  .el-tag, .el-button{
    margin: 0px 0px;    
  }

  .tags{
    .el-button{
      line-height:24px;
    }
  }
}
</style>
