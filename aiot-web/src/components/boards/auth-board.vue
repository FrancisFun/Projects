<!--
 * @FileDescription: 鉴权面板组件
 * @Author: 范承祥
 * @Date: 2021/2/26
 * @LastEditors: 范承祥
 * @LastEditTime: 2021/2/26
 -->

<template>
  <div class="authBoard">
    <el-card shadow="always">
      <div class="logo">
        <img :src="logoSrc"/>
      </div>
      <div class="tab">
        <el-tabs v-model="select" :stretch="true" v-loading="isLoading">
          <el-tab-pane name="login" :label="loginTip">
            <login-board></login-board>
          </el-tab-pane>
          <el-tab-pane name="register" :label="registerTip">
            <register-board></register-board>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-card>
  </div>
</template>

<script>
  import logoSrc from '../../assets/aiot.png'
  import LoginBoard from "./login-board";
  import RegisterBoard from "./register-board";
  export default {
    name: "auth-board",
    components: {RegisterBoard, LoginBoard},
    data() {
      return {
        logoSrc: logoSrc,
        loginTip: "登陆",
        registerTip: "注册",
        select: "login",
      }
    },
    computed: {
      isLoading:function(){
        return this.$store.state.isLoading
      }
    }
  }
</script>

<style scoped>
  .logo {
    margin-top: 20px;
    margin-bottom: 40px;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .tab{
  }

  .logo img {
    height: 30px;
  }

  .authBoard{
    width: 340px;
  }

</style>
