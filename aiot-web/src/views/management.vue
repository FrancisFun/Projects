<!--
 * @FileDescription: 设备看板组件
 * @Author: 范承祥
 * @Date: 2021/2/25
 * @LastEditors: 范承祥
 * @LastEditTime: 2021/2/25
 -->

<template>
  <div>
    <el-row>
      <el-col>
        <myHeader></myHeader>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="4">
        <asideMenu class="hidden-xs-only"></asideMenu>
      </el-col>
      <el-col :xs="24" :span="20">
        <router-view></router-view>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <myFooter></myFooter>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import myHeader from '../components/global/web-header.vue'
  import asideMenu from '../components/menus/aside-menu.vue'
  import myFooter from '../components/global/web-footer.vue'

  export default {
    name: "management",
    components: {
      myHeader,
      asideMenu,
      myFooter
    },
  }
</script>

<style scoped>
  .el-pager li.number{
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
  }
</style>
