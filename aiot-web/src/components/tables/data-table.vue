<!--
 * @FileDescription: 数据表组件
 * @Author: 范承祥
 * @Date: 2019/02/17
 * @LastEditors: 范承祥
 * @LastEditTime: 2020/07/31
 -->
<template>
    <div class="table">
        <el-card shadow="always">
            <el-row class="hidden-xs-only"
                    :gutter="20">
                <el-col>
                        <el-date-picker
                                v-model="dateTimeValue"
                                type="datetimerange"
                                :picker-options="pickerOptions"
                                :range-separator="rangeDesc"
                                :start-placeholder="startDesc"
                                :end-placeholder="endDesc"
                                size="large">
                        </el-date-picker>
                    <el-button type="primary"
                               icon="el-icon-search">
                        {{selectDesc}}
                    </el-button>
                </el-col>
                <el-col>
                    <div class="search">

                    </div>
                </el-col>
<!--                <el-col :span="7">-->
<!--                    <div class="export">-->
<!--                        <el-button icon="el-icon-download">-->
<!--                            {{exportDesc}}-->
<!--                        </el-button>-->
<!--                    </div>-->
<!--                </el-col>-->
            </el-row>
            <el-row class="hidden-sm-and-up">
                <el-col :span="24">
                        <el-date-picker
                                v-model="dateTimeValue"
                                type="datetime"
                                :placeholder="placeholderDesc"
                                size="large">
                        </el-date-picker>
                    <el-button type="primary"
                               icon="el-icon-search"
                               @click="search">
                    </el-button>
                </el-col>
<!--                    <el-col :span="12">-->
<!--                        <div class="export">-->
<!--                            <el-button icon="el-icon-download">-->
<!--                            </el-button>-->
<!--                        </div>-->
<!--                    </el-col>-->
            </el-row>
            <el-table
                    v-loading="loading"
                    stripe
                    :data="tableData"
                    :size="fontSize"
                    :fit=true
                    :show-header="isShowLabel"
                    :tooltip-effect="tooltipTheme">
                <el-table-column
                        v-for="(item,i) in labelList"
                        :fixed="item.isFixed"
                        :key="i"
                        :prop="item.data"
                        :label="item.label"
                        :sortable="isSortable"
                        :show-overflow-tooltip="isShowTip"
                        align="center">
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-row>
                    <el-col class="hidden-xs-only">
                        <el-pagination
                                :hide-on-single-page="isHide"
                                layout="total, sizes, prev, pager, next, jumper"
                                :total="tableData.length"
                                :page-sizes="[10, 20, 50, 100]"
                                :page-size="pageSize"
                                :current-page.sync="currentPage"
                                @size-change="handleSizeChange"
                                @current-change="handleCurrentChange">
                        </el-pagination>
                    </el-col>
                    <el-col class="hidden-sm-and-up">
                        <el-pagination
                                small
                                :hide-on-single-page="isHide"
                                layout="prev, pager, next"
                                :total="tableData.length"
                                :page-size="pageSize"
                                :current-page.sync="currentPage"
                                @current-change="handleCurrentChange">
                        </el-pagination>
                    </el-col>
                </el-row>
            </div>
        </el-card>
    </div>
</template>

<script>
    let self = null

    export default {
        name: "table-view",
        data() {
            return {
                // 日期时间选择题选项
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },

                // 日期时间选择器默认值
                dateTimeValue:[],

                // 日期时间选择器占位符
                placeholderDesc:"选择时间日期",

                // 范围提示
                rangeDesc:"至",

                // 开始时间提示
                startDesc:"起始时间",

                // 结束时间提示
                endDesc:"结束时间",

                // 查询提示
                selectDesc:"查询",

                // 导出提示
                exportDesc:"导出",

                // 数据集
                tableData: [],

                // 是否展示标签
                isShowLabel: true,

                // 是否可排序
                isSortable:false,

                // 标签集
                labelList: [
                    {label: "区域", data: "name", isFixed: true},
                    {label: "采样时间", data: "date", isFixed: true},
                ],

                // 字体大小
                fontSize: "medium",

                // 当前页码
                currentPage: 1,

                // 页面大小
                pageSize: 10,

                // 是否隐藏多余列
                isHide: false,

                // 排序规则
                sortRules:{prop: 'date', order: 'descending'},

                // 是否展示提示
                isShowTip:false,

                // 提示主题
                tooltipTheme:"dark",
            }
        },
        mounted: function () {

        },
        methods: {
            /**
             * 处理页面大小变化
             * @param pageSize 页面大小
             */
            handleSizeChange: function (pageSize) {
                this.pageSize = pageSize
            },

            /**
             * 处理当前页面变化
             * @param currentPage 当前页面
             */
            handleCurrentChange: function (currentPage) {
                this.currentPage = currentPage
            },

            loadData: function(){
                if(this.tableData.length !== 0) {
                    return
                }
                self.$axios.get("/api/data/selectAllByLimit",{
                    params: { offset: 0 }
                })
                .then(res => {
                    res.data.forEach(item => {
                        this.tableData.push(this.convertData(item))
                    })
                }).catch(err => {
                    if(err.status !== 403 && err.status !== 500){
                        this.$message.error(err)
                    }
                })
            },

            convertData: function(origin){
                function Data() {}
                let data = new Data()
                data.name = origin.area.name
                data.date = new Date(origin.timestamp).toLocaleString()
                origin.dataList.forEach(item => {
                    data[item.dataInformation.toString()]=item.value
                })
                return data
            },

            search: function(){
                console.log(this.dateTimeValue)
            }
        },
        computed: {
            loading: function() {
                return this.tableData.length === 0
            }
        },
        created() {
            self = this
            if (self.$store.state.dataInformation.length !== 0) {
                self.$store.state.dataInformation.forEach(item => {
                    self.labelList.push({
                        label: item.name + "（"+item.unit+"）",
                        data: item.id.toString(),
                        isFixed: false
                    })
                })
                self.loadData()
                return
            }
            self.$axios.get("/api/dataInformation/selectAll")
                .then(res => {
                    self.$store.state.dataInformation = res.data
                    self.$store.state.dataInformation.forEach(item => {
                        self.labelList.push({
                            label: item.name + "（"+item.unit+"）",
                            data: item.id,
                            isFixed: false
                        })
                    })
                    self.loadData()
                    console.log("axios load")
                    console.log("Got data information")
                }).catch(err => {
                console.log(err)
            })
        }
    }
</script>

<style scoped>
    .el-row {
        margin-bottom: 10px;
    }

    .el-col {
        border-radius: 5px;
        margin-bottom: 10px;
    }
    .picker{
        text-align: center;
    }

    .search{
        text-align: left;
    }

    .export{
        text-align: right;
    }

    .pagination {
        padding-top: 20px;
        text-align: center;
    }
</style>
