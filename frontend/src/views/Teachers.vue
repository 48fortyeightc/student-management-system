<template>
  <el-card>
    <template #header>
      <div class="toolbar">
        <div class="toolbar__title">教师管理</div>
        <div class="toolbar__filters">
          <el-input
            v-model="searchName"
            placeholder="按姓名搜索"
            clearable
            @keyup.enter="load"
            @clear="load"
            style="width: 180px"
          />
          <el-input
            v-model="searchSubject"
            placeholder="按科目搜索"
            clearable
            @keyup.enter="load"
            @clear="load"
            style="width: 180px"
          />
          <el-button type="primary" :loading="loading" @click="load">搜索</el-button>
          <el-button @click="resetFilters">重置</el-button>
        </div>
        <el-button type="primary" @click="openAdd">新增教师</el-button>
      </div>
    </template>

    <el-table :data="list" border style="width: 100%" :loading="loading">
      <el-table-column prop="id" label="ID" width="70" />
      <el-table-column prop="name" label="姓名" />
      <el-table-column prop="subject" label="科目" />
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

  <el-dialog v-model="visible" title="教师信息" width="420px">
    <el-form label-width="80px">
      <el-form-item label="姓名" required>
        <el-input v-model="form.name" placeholder="请输入姓名" />
      </el-form-item>
      <el-form-item label="科目" required>
        <el-input v-model="form.subject" placeholder="请输入科目" />
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
import { getTeachers, addTeacher, updateTeacher, deleteTeacher } from "../api/teacher";

const list = ref([]);
const loading = ref(false);
const visible = ref(false);
const searchName = ref("");
const searchSubject = ref("");
const form = reactive({ id: null, name: "", subject: "", phone: "" });

const resetForm = () => {
  Object.assign(form, { id: null, name: "", subject: "", phone: "" });
};

const load = async () => {
  loading.value = true;
  try {
    const res = await getTeachers({
      name: searchName.value || undefined,
      subject: searchSubject.value || undefined
    });
    list.value = res.data || [];
  } finally {
    loading.value = false;
  }
};

const resetFilters = () => {
  searchName.value = "";
  searchSubject.value = "";
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
  if (!form.subject) {
    ElMessage.error("请输入科目");
    return false;
  }
  return true;
};

const save = async () => {
  if (!validateForm()) return;

  const request = form.id ? updateTeacher(form.id, form) : addTeacher(form);

  try {
    await request;
    ElMessage.success(form.id ? "教师信息已更新" : "教师已新增");
    visible.value = false;
    await load();
  } catch (error) {
    ElMessage.error("保存失败，请重试");
  }
};

const del = async (id) => {
  try {
    await ElMessageBox.confirm("确定要删除该教师吗？", "提示", { type: "warning" });
    await deleteTeacher(id);
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
