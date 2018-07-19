<template>
  <el-form :model="uploadModel" class="inputFileWrapper" v-loading="onSaving" element-loading-text="正在导入...">
    <el-form-item label="">

      <label for="inputFile">
        <input type="file" id="uploadFile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" @change="onUploadFileChanged" />
        <span class="custorm-style">
          <span class="left-button" @click="onUploadClick">选择文件</span>
          <span class="right-text" id="rightText">{{fileName}}</span>
        </span>
      </label>

      <el-button class="btn-import" :disabled="canSave" type="primary" size="small" @click="onImportExcel">保存</el-button>
      <a href="/static/templates/车型网车型数据搜集.xlsx">获取模板</a>
    </el-form-item>

    <span style='color:red'>目前仅支持excel 品牌模板导入！</span>
    <p>{{message}}</p>

  </el-form>
</template>
<script>
import { importExcell } from "@/api";

require("../../assets/templates/车型网车型数据搜集.xlsx");

export default {
  data() {
    return {
      uploadModel: {
        file: null,
        startRowIndex: 2,
        sheetIndex: 0
      },
      fileName: "",
      onSaving: false,
      message:""
    };
  },
  methods: {
    onUploadClick: function() {
      this.message ="";
      this.$el.querySelector("#uploadFile").click();
    },
    onUploadFileChanged: function(e) {
      this.fileName = e.target.value;

      this.uploadModel.file = e.target.files[0];
     
      console.log(this.fileName);
    },
    onImportExcel: function() {
      this.onSaving = true;

      let formData = new FormData();
      formData.append("file", this.uploadModel.file);
      formData.append("startRowIndex", this.uploadModel.startRowIndex);
      formData.append("sheetIndex", this.uploadModel.sheetIndex);

      importExcell(formData)
        .then(res => {
          this.onSaving = false;
          this.message= res.data;
          if (res.success) {
            this.$message({ type: "success", message: "操作成功" });
          } else {
            this.$message({ type: "warning", message: "操作失败" });
          }
        })
        .catch(e => {
          this.onSaving = false;
          this.$message.error("保存异常");
        });
    }
  },
  computed: {
    canSave: function() {
      return (
        this.fileName == "" ||
        (!this.fileName.endsWith(".xls") && !this.fileName.endsWith(".xlsx")) ||
        this.uploadModel.file == null
      );
    }
  }
};
</script>

<style>
a {
  text-decoration: none;
}

a:hover {
  text-decoration: none;
}
.btn-import {
  vertical-align: middle;
  margin: auto 20px;
}

.inputFileWrapper label {
  display: block;
  float: left;
  position: relative;
}
.inputFileWrapper input[type="file"] {
  position: absolute;
  width: 1px;
  height: 1px;
  clip: rect(0, 0, 0, 0);
}
.inputFileWrapper .custorm-style {
  display: block;
  width: 390px;
  height: 50px;
}
.inputFileWrapper .custorm-style .left-button {
  width: 80px;
  line-height: 50px;
  background: #008ac7;
  color: #fff;
  display: block;
  text-align: center;
  float: left;
}
.inputFileWrapper .custorm-style .right-text {
  width: 300px;
  height: 40px;
  line-height: 50px;
  display: block;
  float: right;
  padding: 4px;
  border: 1px solid #008ac7;
  overflow: hidden;
  -ms-text-overflow: ellipsis;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>

