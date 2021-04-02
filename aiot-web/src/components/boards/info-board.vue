<!--
 * @FileDescription: 信息看板组件
 * @Author: 范承祥
 * @Date: 2019/02/17
 * @LastEditors: 范承祥
 * @LastEditTime: 2020/07/31
 -->
<template>
    <el-card
        shadow="always"
        :body-style="cardStyle">
        <el-row :gutter="20">
            <el-col :span="12">
                <div class="image"><img :src="imgSrc(src)"/></div>
            </el-col>
            <el-col :span="12">
                <p class="title">{{title}}</p>
                <p class="date">{{samplingTime}}</p>
                <p class="date">{{date.toLocaleString()}}</p>
            </el-col>
        </el-row>
        <el-row>
            <el-col>
                <el-table
                        v-loading="loading"
                        :data="tableData"
                        :size="fontSize"
                        :fit=true
                        :show-header="isShowLabel"
                        max-height="200px">
                    <el-table-column prop="area.name" :label="area" align="center">
                    </el-table-column>
                    <el-table-column prop="value" :label="detail" align="center">
                    </el-table-column>
                </el-table>
            </el-col>
        </el-row>
    </el-card>
</template>

<script>
    import globalConfig from "../../../globalConfig";

    let self = null
    export default {
        name: "info-board",
        props: {
            title : String,
            unit : String,
            src : String,
            select : Number
        },
        data() {
            return {
                // 卡片样式
                cardStyle: "padding: 15px",

                // 采样时间提示
                samplingTime: "采样时间：",

                // 采样时间
                date: {},

                // 数据集
                tableData: [],

                // 是否展示标签
                isShowLabel: true,

                // 区域提示
                area: "区域",

                // 字体大小
                fontSize: "medium"
            }
        },
        computed:{
            /**
             * 进行数据单位填充
             * @returns {string} 带单位数据
             */
            detail:function(){
                return "数据（"+this.unit+"）"
            },
            loading: function(){
                return this.tableData.length === 0
            }
        },
        created() {
            self = this
            self.$axios.get("/api/data/selectByInformation",{
                params: { information: this.select, offset: 0 }
            })
            .then(res => {
                res.data.forEach(item => {
                    this.tableData.push(item)
                })
                if(this.tableData.length > 0){
                    this.date = new Date(this.tableData[0].timestamp)
                }
                console.log(self.tableData)
            })
            .catch(err => {
                if(err.status !== 403 && err.status !== 500){
                    this.$message.error(err)
                }
            })
        },
        methods: {
            imgSrc: function(origin) {
                return globalConfig.fileServerHostAndPort+origin
            }
        }
    }
</script>

<style scoped>
    .image{
        padding:0px;
        text-align: center;
    }

    img{
        width:87px;
    }

    .title {
        margin-top: 10px;
        margin-bottom: 0px;
        font-size:20px;
        font-weight: bold;
    }

    .date {
        margin-bottom: 5px;
        margin-top: 5px;
        font-size:10px;
        color: #565656
    }

    .el-row {
        margin-bottom: 20px;
        padding-left: 0px;
        padding-right: 0px;
    }

</style>
