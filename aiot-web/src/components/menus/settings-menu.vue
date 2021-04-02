<!--
 * @FileDescription: 设置菜单组件
 * @Author: 范承祥
 * @Date: 2019/02/17
 * @LastEditors: 范承祥
 * @LastEditTime: 2020/07/31
 -->
<template>
    <el-aside class="settingsMenu">
        <el-row>
            <p class="settingsTitle">{{nodeTitleDesc}}{{title}}</p>
        </el-row>
        <el-row>
            <p class="settingsDesc" v-show="!disabled">{{menuTitleDesc}}</p>
            <p class="stateDesc" v-show="disabled">{{stateDesc}}</p>
        </el-row>
        <div class="settingsMenu"
             v-loading="isLoading"
             v-show="!disabled">
<!--                <div class="rangeItem"-->
<!--                     v-for="(item,i) in thresholdList"-->
<!--                     :key="i">-->
<!--                    <el-row>-->
<!--                        <el-col :span="4">-->
<!--                            <img :src="item.imgSrc">-->
<!--                        </el-col>-->
<!--                        <el-col :span="20">-->
<!--                            <p class="thresholdItemDesc">{{item.name}}</p>-->
<!--                        </el-col>-->
<!--                    </el-row>-->
<!--                    <el-row class="slider">-->
<!--                        <el-slider v-model="item.value"-->
<!--                                   :range="item.isRange"-->
<!--                                   :min="item.min"-->
<!--                                   :max="item.max"-->
<!--                                   :marks="item.marks">-->

<!--                        </el-slider>-->
<!--                    </el-row>-->
<!--                </div>-->
                <el-row v-for="(item,i) in settingsList"
                        :key="i">
                    <el-col :span="4">
                        <img :src="imgSrc(item.icon)">
                    </el-col>
                    <el-col :span="16">
                        <p class="settingsItemDesc">{{item.name}}</p>
                    </el-col>
                    <el-col :span="4">
                        <el-tooltip placement="top">
                            <div slot="content">
                                {{stateDesc}}
                            </div>
                            <el-switch v-model="item.status"
                                       :disabled="moduleDisabled(item.status)"
                                       :active-value="1"
                                       :inactive-value="0"
                                       @change="modify(status,item.id)"></el-switch>
                        </el-tooltip>
                    </el-col>
                </el-row>
        </div>
    </el-aside>
</template>

<script>
    import globalConfig from "../../../globalConfig";

    export default {
        name: "settings-menu",
        props: {
            title: String,
            status: Number,
            modules: Array
        },
        data() {
            return {
                // 菜单标题
                nodeTitleDesc: "节点",

                // 菜单标题
                menuTitleDesc: "控制面板",

                // 滑动设置项集
                // thresholdList:[
                //     {name:"光照范围（lux）",imgSrc:light,value:[20,30],isRange:true,marks:{0:"0",20:"20",40:"40"},min:0,max:40},
                //     {name:"CO2范围（ppm）",imgSrc:co2,value:[10,20],isRange:true,marks:{5:"5",15:"15",25:"25"},min:5,max:25},
                //     {name:"空气温度范围（℃）",imgSrc:temperature,value:[10,20],isRange:true,marks:{0:"0",20:"20",40:"40"},min:0,max:40},
                //     {name:"土壤湿度范围（%）",imgSrc:moisture,value:[50,75],isRange:true,marks:{0:"0",20:"20",50:"50",75:"75",100:"100"},min:0,max:100},
                //     {name:"土壤压力范围（N）",imgSrc:pressure,value:[1,3],isRange:true,marks:{0:"0",2:"2",5:"5",7:"7",100:"10"},min:0,max:10},
                //     {name:"土壤PH值范围（1）",imgSrc:ph,value:[6,7],isRange:true,marks:{0:"0",7:"7",14:"14"},min:0,max:14},
                // ],

                // 设置项集
                settingsList: this.modules,

                isLoading: false
            }
        },
        methods: {
          moduleDisabled: function(status) {
              return status===2
          },
            imgSrc: function(origin) {
              return globalConfig.fileServerHostAndPort+origin
            },
            modify: function(status, id) {
              this.isLoading = true
                this.$axios.post('/api/module/modify', {
                    status: status,
                    id: id
                })
                    .then(res => {
                        if(res.status === 200) {
                            this.$message({
                                message: "模块状态更新成功",
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
            }
        },
        computed:{
            /**
             * 查询设置项是否可用
             * @returns {boolean|*} 设置项是否可用
             */
            disabled:function(){
                let check = false
                if(localStorage.role){
                    check = localStorage.role !== "ROLE_SU"
                    console.log(check)
                }
                return this.currentStatus === 2 || check;
            },

            /**
             * 状态提示转换
             * @returns {string}
             */
            stateDesc:function(){
                switch (this.status) {
                    case 0:
                        return "已关闭"
                    case 1:
                        return "已启动"
                    case 2:
                        return "故障"
                    default:
                        return "未知状态"
                }
            }
        },
    }
</script>

<style scoped>
    .el-row {
        padding: 4% 10%;
    }

    .el-row .slider{
        padding-top:0px;
    }

    .settingsMenu{
        height:140%;
    }

    img {
        margin-top: 0px;
        width: 75%;
    }

    p {
        margin-top: 0px;
        margin-bottom:0px;
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
    }

    .settingsTitle {
        color: #597EF7;
        font-size: 20px;
        font-weight: bold;
    }

    .settingsDesc,.stateDesc{
        font-weight: bold;
    }
</style>
