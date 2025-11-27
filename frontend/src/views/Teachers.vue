<template>
  <el-card>
    <template #header>
      <div style="display: flex; justify-content: space-between">
        <span>教师管理</span>
        <el-button type="primary" @click="openAdd">新增教师</el-button>
      </div>
    </template>

    <el-table :data="list" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="70" />
      <el-table-column prop="name" label="姓名" />
      <el-table-column prop="subject" label="科目" />
      <el-table-column prop="phone" label="电话" />
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button size="small" @click="openEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>

  <el-dialog v-model="visible" title="教师信息">
    <el-form label-width="70px">
      <el-form-item label="姓名"><el-input v-model="form.name" /></el-form-item>
      <el-form-item label="科目"><el-input v-model="form.subject" /></el-form-item>
      <el-form-item label="电话"><el-input v-model="form.phone" /></el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="visible=false">取消</el-button>
      <el-button type="primary" @click="save">保存</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref } from "vue";
import { getTeachers, addTeacher, updateTeacher, deleteTeacher } from "../api/teacher";

const list = ref([]);
const visible = ref(false);
const form = ref({});

function load() {
  getTeachers().then(res => {
    list.value = res.data;
  });
}

function openAdd() {
  form.value = {};
  visible.value = true;
}

function openEdit(row) {
  form.value = JSON.parse(JSON.stringify(row));
  visible.value = true;
}

function save() {
  if (form.value.id) {
    updateTeacher(form.value.id, form.value).then(load);
  } else {
    addTeacher(form.value).then(load);
  }
  visible.value = false;
}

function del(id) {
  deleteTeacher(id).then(load);
}

load();
</script>
