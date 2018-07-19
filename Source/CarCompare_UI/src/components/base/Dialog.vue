<template>
    <el-dialog class="icare-dialog" :title="title" :visible.sync="visible" :width="width" :close-on-click-modal="false" :before-close="onClose" append-to-body>
        <slot name="content"></slot>
        <div slot="footer" class="dialog-footer">
            <el-button size="small" @click="cancel">取 消</el-button>
            <el-button type="primary" size="small" @click="submit('editForm')">确 定</el-button>
        </div>
    </el-dialog>   
</template>
<script>
export default {
  name: "Dialog",
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: '[标题]'
    },
    width: {
      type: String,
      default: '500px'
    }
  },
  data () {
    return {
    }
  },
  methods: {
    cancel () {
        this.$emit('cancel')
    },
    submit(){
        this.$emit('submit')
    },
    onClose(){      
        this.$emit('cancel')
    }
  }
}
</script>
