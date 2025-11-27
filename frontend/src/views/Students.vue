<template>
  <el-card>
    <template #header>
      <div class="toolbar">
        <div class="toolbar__title">学生管理</div>
        <div class="toolbar__filters">
          <el-input
            v-model="searchName"
            placeholder="按姓名搜索"
            clearable
            @keyup.enter="load"
            @clear="load"
            style="width: 180px"
          />
          <el-select
            v-model="searchGender"
            placeholder="性别"
            clearable
            style="width: 140px"
            @change="load"
          >
            <el-option label="男" value="男" />
            <el-option label="女" value="女" />
          </el-select>
          <el-button type="primary" :loading="loading" @click="load">搜索</el-button>
          <el-button @click="resetFilters">重置</el-button>
        </div>
        <el-button type="primary" @click="openAdd">新增学生</el-button>
      </div>
    </template>

    <el-table :data="list" border style="width: 100%" :loading="loading">
      <el-table-column prop="id" label="ID" width="70" />
      <el-table-column prop="name" label="姓名" />
      <el-table-column prop="gender" label="性别" width="90" />
      <el-table-column prop="age" label="年龄" width="90" />
      <el-table-column prop="phone" label="电话" />
      <el-table-column prop="createTime" label="创建时间" min-width="180" />
      <el-table-column label="操作" width="200">
        <template #default="scope">
          <el-button size="small" @click="openEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="del(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>

  <!-- 弹窗 -->
  <el-dialog v-model="visible" title="学生信息" width="420px">
    <el-form label-width="80px">
      <el-form-item label="姓名" required>
        <el-input v-model="form.name" placeholder="请输入姓名" />
      </el-form-item>
      <el-form-item label="性别" required>
        <el-select v-model="form.gender" placeholder="请选择性别" style="width: 100%">
          <el-option label="男" value="男" />
          <el-option label="女" value="女" />
        </el-select>
      </el-form-item>
      <el-form-item label="年龄" required>
        <el-input v-model="form.age" type="number" placeholder="请输入年龄" />
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" placeholder="请输入联系方式" />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="visible=false">取消</el-button>
      <el-button type="primary" @click="save">保存</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { onMounted, reactive, ref } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { getStudents, addStudent, updateStudent, deleteStudent } from "../api/student";

const list = ref([]);
const loading = ref(false);
const visible = ref(false);
const searchName = ref("");
const searchGender = ref("");
const form = reactive({ id: null, name: "", gender: "", age: null, phone: "" });

const resetForm = () => {
  Object.assign(form, { id: null, name: "", gender: "", age: null, phone: "" });
};

const load = async () => {
  loading.value = true;
  try {
    const res = await getStudents({
      name: searchName.value || undefined,
      gender: searchGender.value || undefined
    });
    list.value = res.data || [];
  } finally {
    loading.value = false;
  }
};

const resetFilters = () => {
  searchName.value = "";
  searchGender.value = "";
  load();
};

const openAdd = () => {
  resetForm();
  visible.value = true;
};

const openEdit = (row) => {
  Object.assign(form, row);
  visible.value = true;
};

const validateForm = () => {
  if (!form.name) {
    ElMessage.error("请输入姓名");
    return false;
  }
  if (!form.gender) {
    ElMessage.error("请选择性别");
    return false;
  }
  const ageNumber = Number(form.age);
  if (!Number.isInteger(ageNumber) || ageNumber <= 0) {
    ElMessage.error("请填写正确的年龄");
    return false;
  }
  return true;
};

const save = async () => {
  if (!validateForm()) return;

  const payload = { ...form, age: Number(form.age) };
  const request = form.id ? updateStudent(form.id, payload) : addStudent(payload);

  try {
    await request;
    ElMessage.success(form.id ? "学生信息已更新" : "学生已新增");
    visible.value = false;
    await load();
  } catch (error) {
    ElMessage.error("保存失败，请重试");
  }
};

const del = async (id) => {
  try {
    await ElMessageBox.confirm("确定要删除该学生吗？", "提示", { type: "warning" });
    await deleteStudent(id);
    ElMessage.success("删除成功");
    load();
  } catch (error) {
    if (error !== "cancel") {
      ElMessage.error("删除失败，请稍后重试");
    }
  }
};

onMounted(load);
</script>

<style scoped>
.toolbar {
  display: flex;
  align-items: center;
  gap: 12px;
}

.toolbar__title {
  font-weight: 600;
  font-size: 16px;
}

.toolbar__filters {
  display: flex;
  align-items: center;
  gap: 8px;
  flex: 1;
}
</style>
