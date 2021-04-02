<!--
 * @FileDescription: 设备看板组件
 * @Author: 范承祥
 * @Date: 2021/2/25
 * @LastEditors: 范承祥
 * @LastEditTime: 2021/2/25
 -->

<template>
  <div class="registerBoard">
    <el-form ref="registerForm"
             :model="user"
             :rules="rules"
             status-icon>
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
      <el-form-item prop="confirm">
        <el-input v-model="user.confirm"
                  :placeholder="confirmPasswordPlaceholder"
                  show-password
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"
                   @click="submit('registerForm')"
        >{{registerTip}}
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "register-board",
    data() {
      let validateUsername = (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请输入用户名"));
        } else if (value.length > rule.maxlength) {
          callback(new Error("用户名长度不超过20个字符"));
        } else {
          if (this.user.username !== "") {
            this.$refs.registerForm.validateField("password");
          }
          callback();
        }
      }
      let validatePass = (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请输入密码"));
        } else if (value.length < rule.minlength) {
          callback(new Error("密码长度不得低于8位"));
        } else if (value.length > rule.maxlength) {
          callback(new Error("密码长度不得超过20位"));
        } else {
          if (this.user.password !== "") {
            this.$refs.registerForm.validateField("confirm");
          }
          callback();
        }
      }
      let validatePass2 = (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请再次输入密码"));
        } else if (value !== this.user.password) {
          callback(new Error("两次输入密码不一致!"));
        } else {
          callback();
        }
      }
      return {
        usernamePlaceholder: "用户名",
        passwordPlaceholder: "密码",
        confirmPasswordPlaceholder: "确认密码",
        registerTip: "注册",

        user: {
          username: "",
          password: "",
          confirm: "",
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
          confirm: [
            {
              validator: validatePass2,
              trigger: 'blur'
            }
          ],
        }
      }
    },
    methods: {
      submit: function (formName) {
        this.$refs[formName].validate((valid) => {
          this.$store.state.isLoading = true
          if (valid) {
            this.$axios.post('/api/user/register', this.user)
                .then(res => {
                  if (res.status === 200) {
                    this.$message({
                      message: "注册成功",
                      type: "success"
                    })
                    localStorage.setItem("token", res.headers.authorization)
                    localStorage.setItem("role",res.data.role)
                    this.$store.state.userInfo = res.data
                    console.log(this.$store.state.userInfo)
                    this.$store.state.isLoading = false
                    this.$router.push({path: '/management'});
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
      resetForm: function (formName) {
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

  .right {
    text-align: right;
  }
</style>
