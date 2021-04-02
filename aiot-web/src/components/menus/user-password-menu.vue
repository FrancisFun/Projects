<!--
 * @FileDescription: 用户密码组件
 * @Author: 范承祥
 * @Date: 2019/02/17
 * @LastEditors: 范承祥
 * @LastEditTime: 2020/07/31
 -->
<template>
    <div class="password">
        <p class="infoTitle">{{titleDesc}}</p>
        <el-form ref="changePasswordForm"
                 v-loading="isLoading"
                 :model="passwordDto"
                 :rules="rules"
                 label-position="right"
                 label-width="30%">
            <el-form-item :label="oldPasswordDesc"
                          prop="oldPassword">
                <el-input v-model="passwordDto.oldPassword"
                          type="password"
                          show-password
                ></el-input>
            </el-form-item>
            <el-form-item :label="newPasswordDesc"
                          prop="newPassword">
                <el-input v-model="passwordDto.newPassword"
                          type="password"
                          show-password
                ></el-input>
            </el-form-item>
            <el-form-item :label="confirmPasswordDesc"
                          prop="confirmPassword">
                <el-input v-model="passwordDto.confirmPassword"
                          type="password"
                          show-password
                ></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary"
                           @click="submit('changePasswordForm')"
                >{{summitDesc}}</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "user-password",
        data(){
            let validateOldPassword = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error("请输入原密码"));
                } else if(value.length < rule.minlength) {
                    callback(new Error("密码长度不得低于8位"));
                } else if(value.length > rule.maxlength) {
                    callback(new Error("密码长度不得超过20位"));
                } else {
                    if (this.passwordDto.oldPassword !== "") {
                        this.$refs.changePasswordForm.validateField("newPassword");
                    }
                    callback();
                }
            }
            let validateNewPassword = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error("请输入新密码"));
                } else if(value.length < rule.minlength) {
                    callback(new Error("密码长度不得低于8位"));
                } else if(value.length > rule.maxlength) {
                    callback(new Error("密码长度不得超过20位"));
                } else {
                    if (this.passwordDto.newPassword !== "") {
                        this.$refs.changePasswordForm.validateField("confirmPassword");
                    }
                    callback();
                }
            }
            let validateConfirmPassword = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error("请再次输入密码"));
                } else if (value !== this.passwordDto.newPassword) {
                    callback(new Error("两次输入密码不一致!"));
                } else {
                    callback();
                }
            }
            return {
                // 修改密码提示
                titleDesc: "密码修改",

                // 原密码提示
                oldPasswordDesc: "原密码",

                // 新密码提示
                newPasswordDesc: "新密码",

                // 确认密码提示
                confirmPasswordDesc: "确认密码",

                // 确认提示
                summitDesc: "确认",

                cancelDesc: "取消",

                isLoading: false,

                passwordDto: {
                    id: this.$store.state.userInfo.id,
                    // 原密码
                    oldPassword: "",

                    // 新密码
                    newPassword: "",

                    // 确认密码
                    confirmPassword: "",
                },

                rules: {
                    oldPassword: [
                        {
                            validator: validateOldPassword,
                            minlength: 8,
                            maxlength: 20,
                            trigger: 'blur'
                        }
                    ],
                    newPassword: [
                        {
                            validator: validateNewPassword,
                            minlength: 8,
                            maxlength: 20,
                            trigger: 'blur'
                        }
                    ],
                    confirmPassword: [
                        {
                            validator: validateConfirmPassword,
                            trigger: 'blur'
                        }
                    ],
                },
            }
        },
        methods:{
            /**
             * 确认修改密码
             */
            submit: function(formName){
                this.isLoading = true
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/api/user/changePassword', this.passwordDto)
                            .then(res => {
                                if(res.status === 200) {
                                    this.$message({
                                        message: "修改密码成功",
                                        type: "success"
                                    })
                                    this.isLoading = false
                                }
                            })
                            .catch(err => {
                                if(err.status !== 403 && err.status !== 500){
                                    this.$message.error(err)
                                }
                                this.isLoading = false
                            })
                    } else {
                        return false;
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .password{
        padding-left:20px;
        padding-right:20px;
    }

    .infoTitle {
        color: #597EF7;
        font-size: 20px;
        font-weight: bold;
    }
</style>
