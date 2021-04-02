<!--
 * @FileDescription: 设备看板组件
 * @Author: 范承祥
 * @Date: 2019/02/17
 * @LastEditors: 范承祥
 * @LastEditTime: 2020/07/31
 -->
<template>
    <el-card shadow="always">
        <el-row :gutter="20">
            <el-col :span="6">
                <transition name="el-fade-in">
                    <img class="deviceState" :src="stateImg"/>
                </transition>
            </el-col>

            <el-col :span="12">
                <p class="deviceName">{{area}}</p>
                <p class=""></p>
            </el-col>
            <el-col :span="6">
                <div class="deviceSwitch">
                    <el-tooltip
                            :content="tip"
                            placement="top">
                        <el-switch v-model="currentStatus"
                                   :disabled="disabled"
                                   :active-text="activeDesc"
                                   :inactive-text="inactiveDesc"
                                   :active-value="1"
                                   :inactive-value="0"
                                   @change="modify(status,id)"></el-switch>
                    </el-tooltip>
                </div>
            </el-col>
        </el-row>
        <!-- 设备信息 -->
        <el-row>
            <p class="infoItem">{{stateDesc}}: {{tip}}</p>
            <p class="infoItem"
               v-for="(item,i) in deviceInfoList"
               :key="i">
                {{item.name}}: {{item.data}}
            </p>
        </el-row>
        <!-- 开启节点数 -->
        <el-row>
            <p class="nodeDesc"
               v-show="isShow">
                {{nodeDesc}}: {{this.nodes.length}}
            </p>
        </el-row>
        <!-- 折叠面板 -->
        <el-collapse v-show="isShow">
            <el-collapse-item>
                <span class="collapse-title" slot="title">
                    <div class="moreDesc">
                        <p>{{moreDesc}}</p>
                    </div>
                </span>
                <div class="nodeList">
                    <div class="nodeItem"
                         v-for="(item,i) in this.nodes"
                         :key="i"
                         @click="isDrawerOpen(item)">
                        <div class="nodeItemDesc">
                            <p>{{item.deviceId}}</p>
                        </div>
                        <div class="nodeState">
                            <img :src="nodeStateImg(item.status)"/>
                        </div>
                    </div>
                </div>
            </el-collapse-item>
        </el-collapse>
        <el-drawer :size="settingsMenuSize"
                   :visible.sync="isSettingsOpen">
            <settings-menu :title=currentNode.deviceId
                          :status=currentNode.status
                          :modules=currentNode.modules
            ></settings-menu>
        </el-drawer>
    </el-card>
</template>

<script>
    import on from '../../assets/on.png'
    import off from '../../assets/off.png'
    import error from '../../assets/error.png'
    import question from '../../assets/question.png'
    import settingsMenu from '../menus/settings-menu.vue'
    import SettingsMenu from "../menus/settings-menu";

    export default {
        name: "device-board",
        components: {SettingsMenu},
        props: {
            id: Number,
            area: String,
            deviceId: String,
            status:Number,
            firmwareVersion:String,
            hardwareVersion:String,
            softwareVersion:String,
            mac:String,
            nodes:Array
        },
        // components: {
        //     settingsMenu
        // },
        data() {
            return {
                // 开关开启描述
                activeDesc: "",

                // 开关关闭描述
                inactiveDesc: "",

                // 设置菜单大小
                settingsMenuSize: "330",

                // 设置菜单是否开启
                isSettingsOpen: false,

                // 状态提示
                stateDesc: "运行状态",

                // 模块提示
                nodeDesc: "节点总数",

                moreDesc: "详情",

                // 设备信息集
                deviceInfoList: [
                    {name: "设备号", data: this.deviceId},
                    {name: "固件版本", data: this.firmwareVersion},
                    {name: "硬件版本", data: this.hardwareVersion},
                    {name: "软件版本", data: this.softwareVersion},
                    {name: "MAC地址", data: this.mac},
                ],

                currentStatus: this.status,

                currentNode: {},
            }
        },
        methods: {
            /**
             * 抽屉是否打开
             */
            isDrawerOpen: function (item) {
                this.isSettingsOpen = true
                this.currentNode = item
            },

            nodeStateImg: function (status) {
                switch (status) {
                    case 0:
                        return off
                    case 1:
                        return on
                    case 2:
                        return error
                    default:
                        return question
                }
            },
            modify: function(status, id) {
                this.$axios.post('/api/device/modify', {
                    status: status,
                    id: id
                })
                    .then(res => {
                        if(res.status === 200) {
                            this.$message({
                                message: "设备状态更新成功",
                                type: "success"
                            })
                        }
                    })
                    .catch(err => {
                        if(err.status !== 403 && err.status !== 500){
                            this.$message.error(err)
                        }
                    })
            }
        },
        computed: {
            /**
             * 切换图标
             */
            stateImg: function () {
                switch (this.currentStatus) {
                    case 0:
                        return off
                    case 1:
                        return on
                    case 2:
                        return error
                    default:
                        return question
                }
            },

            /**
             * 切换提示
             * @returns {string} 提示
             */
            tip: function () {
                switch (this.currentStatus) {
                    case 0:
                        return "已关闭"
                    case 1:
                        return "已启动"
                    case 2:
                        return "故障"
                    default:
                        return "未知状态"
                }
            },

            /**
             * 获得设置项列表
             * @returns {*} 设置项列表
             */
            settingsList: function () {
                return settingsMenu.data().settingsList
            },

            /**
             * 是否展示详情
             * @returns {*|boolean} 是否展示
             */
            isShow: function () {
                return this.currentStatus===1
            },

            disabled: function () {
                let check = false
                if(localStorage.role){
                     check = localStorage.role !== "ROLE_SU"
                    console.log(check)
                }
                return this.currentStatus === 2 || check;
            }
        },
    }
</script>

<style scoped>
    .nodeList{
        padding-left:20px;
        padding-right: 20px;
    }

    .nodeItem {
        display: flex;
        align-items: center;
    }

    .infoItem {
        color: #565656;
        font-size: 14px;
        padding-left: 20px;
    }

    .moreDesc{

    }

    .nodeItemDesc {
    }

    .nodeState {
        margin-left: auto;
    }

    .nodeState img{
        width: 13px;
    }

    .nodeDesc{
        color: #565656;
        font-size: 14px;
        font-weight: bold;
        text-align: center;
    }

    .deviceState {
        width: 100%;
    }

    .deviceSwitch {
        float: right;
    }

    .deviceName {
        font-size: larger;
        font-weight: bold;
    }

    .settingsImg {
        float: right;
    }

    .settingsImg i {
        font-size: large;
    }

    .collapse-title {
        padding-left: 20px;
        flex: 1 0 90%;
        order: -1;
    }

    .el-collapse-item__header {
        padding-right: 20px;
        flex: 1 0 auto;
        order: 1;
    }

</style>
