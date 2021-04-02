<!--
 * @FileDescription: 用户表格组件
 * @Author: 范承祥
 * @Date: 2020/03/06
 * @LastEditors: 范承祥
 * @LastEditTime: 2020/07/31
 -->
<template>
    <el-aside class="userForm">
        <p class="infoTitle">{{titleDesc}}</p>
        <el-form ref="userForm"
                 v-loading="isLoading"
                 :model="user"
                 label-position="right"
                 label-width="30%">
            <el-form-item :label="avatarDesc">
                <el-upload ref="image"
                           class="avatar-uploader"
                           action="#"
                           accept="image/*"
                           :on-change="addImage"
                           :show-file-list="false"
                           :on-success="handleAvatarSuccess"
                           :auto-upload="false">
                    <el-avatar :size="avatarSize">
                        <img v-if="converter.src(user.avatar)" :src="converter.src(user.avatar)" class="avatar">
                        <i v-else class="el-icon-plus"></i>
                    </el-avatar>
                </el-upload>
            </el-form-item>
            <el-form-item :label="phoneDesc">
                <el-input v-model="user.phone"></el-input>
            </el-form-item>
            <el-form-item :label="emailDesc">
                <el-input v-model="user.email"></el-input>
            </el-form-item>
            <el-form-item :label="roleDesc">
                <el-select v-model="user.role">
                    <el-option v-for="(item,i) in roleOptions"
                               :key="i"
                               :label="item.label"
                               :value="item.value"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item :label="statusDesc">
                <el-select v-model="user.status">
                    <el-option v-for="(item,i) in statusOptions"
                               :key="i"
                               :label="item.label"
                               :value="item.value"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                    <el-button type="primary" @click="onSubmit('userForm')">{{submitDesc}}</el-button>
            </el-form-item>
        </el-form>
    </el-aside>
</template>

<script>
    import Converter from "../../util/Converter";
    export default {
        name: "user-dialog",
        props: {
            id: Number,
            avatar: String,
            username: String,
            role: String,
            status: Number,
            phone: String,
            email: String
        },
        data(){
            return{
                // 标签位置
                labelPosition:"right",

                // 头像提示
                avatarDesc:"头像",

                avatarSize: 30,

                // 用户信息提示
                titleDesc:"用户信息",

                // 联系电话提示
                phoneDesc:"联系电话",

                // 电子邮件提示
                emailDesc:"电子邮件",

                // 等级提示
                roleDesc:"等级",

                // 等级提示
                statusDesc:"状态",

                // 确认提示
                submitDesc:"确认",

                cancelDesc: "取消",

                converter: Converter,

                user: {
                    id: this.id,
                    avatar: this.avatar,
                    username: this.username,
                    role: this.role,
                    status: this.status,
                    phone: this.phone,
                    email: this.email
                },

                roleOptions: [
                    {label: "超级管理员", value: "ROLE_SU"},
                    {label: "管理员", value: "ROLE_ADMIN"},
                    {label: "监督员", value: "ROLE_MONITOR"}
                ],

                statusOptions: [
                    {label: "冻结", value: 0},
                    {label: "正常", value: 1},
                    {label: "已注销", value: 2}
                ],

                imageList: [],

                isLoading: false
            }
        },
        methods:{
            /**
             * 处理上传头像
             * @param res
             * @param file
             */
            handleAvatarSuccess(res, file) {
                this.imageUrl = URL.createObjectURL(file.raw);
            },

            // 添加图片回调
            addImage(file, fileList){
                //验证文件大小
                const sizeIsSatisfy = file.size < 2 * 1024 * 1024;
                if(sizeIsSatisfy){
                    this.imageList=fileList
                    return true
                }else{
                    fileList.pop()
                    this.imageList=fileList
                    this.$message.warning(`超出最大图片大小2M` )
                    return false
                }
            },

            uploadFile: function(formName) {
                let data = new FormData()

                this.imageList.forEach(item => {
                    data.append("files", item.raw)
                })

                // 图片上传
                this.$axios({
                    method: "post",
                    url: '/api/file/upload',
                    data: data,
                    headers: {
                        'Content-Type': 'multipart/form-data',
                    },
                }).then(res => {
                    this.user.avatar = res.data[0].path
                    this.modify(formName)
                    console.log(this.user.avatar)
                    // 清空已上传文件列表
                    this.$refs.image.clearFiles()
                }).catch(err => {
                    console.log(err)
                    this.isLoading = false
                })
            },

            modify: function(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/api/user/modify', this.user)
                            .then(res => {
                                if(res.status === 200) {
                                    this.$message({
                                        message: "更改用户信息成功",
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
            },

            /**
             * 提交更改
             */
            onSubmit:function(formName){
                this.isLoading = true
                if(this.imageList.length !== 0) {
                    this.uploadFile(formName)
                }else{
                    this.modify(formName)
                }

            },
        }
    }
</script>

<style scoped>
    .userForm{
        padding-left:20px;
        padding-right:20px;
    }

    .infoTitle {
        color: #597EF7;
        font-size: 20px;
        font-weight: bold;
    }
</style>
