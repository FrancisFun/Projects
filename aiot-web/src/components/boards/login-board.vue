<!--
 * @FileDescription: 登陆面板组件
 * @Author: 范承祥
 * @Date: 2021/2/25
 * @LastEditors: 范承祥
 * @LastEditTime: 2021/2/25
 -->

<template>
  <div class="loginBoard">
    <el-form ref="loginForm" :model="user" :rules="rules">
      <el-form-item prop="username">
        <el-input v-model="user.username"
                  :placeholder="usernamePlaceholder"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="user.password"
                  :placeholder="passwordPlaceholder"
                  show-password
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-col :span="12">
          <el-checkbox v-model="user.isRemember">{{isRememberTip}}</el-checkbox>
        </el-col>
        <!--      <el-col class="right" :span="12">-->
        <!--        <el-link type="info">{{forgetPasswordTip}}</el-link>-->
        <!--      </el-col>-->
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit('loginForm')">{{loginTip}}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

  export default {
    name: "login-board",
    data() {
      let validateUsername = (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请输入用户名"));
        } else if(value.length > rule.maxlength) {
          callback(new Error("用户名长度不超过20个字符"));
        } else {
          if (this.user.username !== "") {
            this.$refs.loginForm.validateField("password");
          }
          callback();
        }
      }
      let validatePass = (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请输入密码"));
        } else if(value.length < rule.minlength) {
          callback(new Error("密码长度不得低于8位"));
        } else if(value.length > rule.maxlength) {
          callback(new Error("密码长度不得超过20位"));
        } else {
          callback();
        }
      }
      return {
        usernamePlaceholder: "用户名",
        passwordPlaceholder: "密码",
        isRememberTip: "记住我",
        forgetPasswordTip: "忘记密码",
        loginTip: "登陆",

        user: {
          username: "",
          password: "",
          isRemember: false
        },

        rules: {
          username: [
              {
              validator: validateUsername,
              maxlength: 20,
              trigger: 'blur'
            }
          ],
          password: [
            {
              validator: validatePass,
              minlength: 8,
              maxlength: 20,
              trigger: 'blur'
            }
          ],
        }
      }
    },
    methods: {
      submit: function(formName){
        this.$refs[formName].validate((valid) => {
          this.$store.state.isLoading = true
          if (valid) {
            this.$axios.post('/api/user/login', this.user)
                .then(res => {
                  if(res.status === 200){
                    this.$message({
                      message: "登陆成功",
                      type: "success"
                    })
                    localStorage.setItem("token",res.headers.authorization)
                    localStorage.setItem("role",res.data.role)
                    this.$store.state.isLoading = false
                    this.$router.push({ path: '/management' })
                  }
                })
                .catch(err => {
                  if(err !== ""){
                    this.$message.error(err)
                  }
                  this.$store.state.isLoading = false
                })
          } else {
            return false;
          }
        })

      },
      resetForm: function(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style scoped>
  .el-row {
    margin-bottom: 10px;
  }

  .el-button {
    width: 100%;
  }

  .right{
    text-align: right;
  }
</style>
