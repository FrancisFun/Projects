<!--
 * @FileDescription: 设备看板组件
 * @Author: 范承祥
 * @Date: 2021/2/25
 * @LastEditors: 范承祥
 * @LastEditTime: 2021/2/25
 -->

<template>
  <div class="auth">
    <auth-board></auth-board>
  </div>
</template>

<script>
  import AuthBoard from "../components/boards/auth-board";
  export default {
    name: "login",
    components: {AuthBoard}
  }
</script>

<style scoped>
  .auth {
    background: url("../assets/background.jpg");
    background-size: cover;
    height: 100%;
    margin: 0;
    padding: 0;
    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>
