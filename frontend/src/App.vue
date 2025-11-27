<template>
  <el-container style="height: 100vh">
    <el-header style="background: #409EFF; color: white; font-size: 22px;">
      学生管理系统
    </el-header>

    <el-container>
      <el-aside width="200px" style="background: #f2f2f2">
        <el-menu router>
          <el-menu-item index="/">首页</el-menu-item>
          <el-menu-item index="/students">学生管理</el-menu-item>
          <el-menu-item index="/teachers">教师管理</el-menu-item>
        </el-menu>
      </el-aside>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
</script>
