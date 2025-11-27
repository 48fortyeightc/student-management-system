<template>
  <el-card>
    <template #header>
      <div style="display: flex; justify-content: space-between">
        <span>学生管理</span>
        <el-button type="primary" @click="openAdd">新增学生</el-button>
      </div>
    </template>

    <el-table :data="list" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="70" />
      <el-table-column prop="name" label="姓名" />
      <el-table-column prop="gender" label="性别" />
      <el-table-column prop="age" label="年龄" />
      <el-table-column prop="phone" label="电话" />
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button size="small" @click="openEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>

  <!-- 弹窗 -->
  <el-dialog v-model="visible" title="学生信息">
    <el-form label-width="70px">
      <el-form-item label="姓名"><el-input v-model="form.name" /></el-form-item>
      <el-form-item label="性别"><el-input v-model="form.gender" /></el-form-item>
      <el-form-item label="年龄"><el-input v-model="form.age" /></el-form-item>
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
import { getStudents, addStudent, updateStudent, deleteStudent } from "../api/student";

const list = ref([]);
const visible = ref(false);
const form = ref({});

function load() {
  getStudents().then(res => {
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
    updateStudent(form.value.id, form.value).then(load);
  } else {
    addStudent(form.value).then(load);
  }
  visible.value = false;
}

function del(id) {
  deleteStudent(id).then(load);
}

load();
</script>
