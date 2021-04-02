<!--
 * @FileDescription: 折线图组件
 * @Author: 范承祥
 * @Date: 2019/02/17
 * @LastEditors: 范承祥
 * @LastEditTime: 2020/07/31
 -->
<template>
    <el-card shadow="always">
        <el-row :gutter="20">
            <el-date-picker
                    v-model="dateTimeValue"
                    type="datetimerange"
                    :picker-options="pickerOptions"
                    :range-separator="rangeDesc"
                    :start-placeholder="startDesc"
                    :end-placeholder="endDesc"
                    size="large">
            </el-date-picker>
            <el-cascader
                    :options="optionItems"
                    :props="props"
                    collapse-tags
                    clearable>
            </el-cascader>
            <el-button type="primary"
                       icon="el-icon-search">
                {{selectDesc}}
            </el-button>
        </el-row>
        <div id="lineChart">
        </div>
    </el-card>
</template>

<script>
    import * as G2 from '@antv/g2';
    import infoBoard from '../boards/info-board.vue'


    export default {
        name: "line-chart",
        components: {
        },
        props:{
          dataSet:Array,
        },
        data() {
            return {
                chart: null,
                // 数据集
                tableData: infoBoard.data().tableData,

                // 日期时间选择器选项
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

                // 默认日期时间
                dateTimeValue:"",

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

                // 选项集
                optionItems: [
                    {
                        value: 1,
                        label: "1号棚",
                        children: [
                            {value: 2, label: "光照强度"},
                            {value: 3, label: "CO2浓度"},
                            {value: 4, label: "空气温度"},
                            {value: 5, label: "土壤湿度"},
                            {value: 6, label: "土壤压力"},
                            {value: 7, label: "土壤PH"}
                        ]
                    }, {
                        value: 8,
                        label: "2号棚",
                        children: [
                            {value: 9, label: "光照强度"},
                            {value: 10, label: "CO2浓度"},
                            {value: 11, label: "空气温度"},
                            {value: 12, label: "土壤湿度"},
                            {value: 13, label: "土壤压力"},
                            {value: 14, label: "土壤PH"}
                        ]
                    }, {
                        value: 15,
                        label: "3号棚",
                        children: [
                            {value: 16, label: "光照强度"},
                            {value: 17, label: "CO2浓度"},
                            {value: 18, label: "空气温度"},
                            {value: 19, label: "土壤湿度"},
                            {value: 20, label: "土壤压力"},
                            {value: 21, label: "土壤PH"}
                        ]
                    }, {
                        value: 22,
                        label: "4号棚",
                        children: [
                            {value: 23, label: "光照强度"},
                            {value: 24, label: "CO2浓度"},
                            {value: 25, label: "空气温度"},
                            {value: 26, label: "土壤湿度"},
                            {value: 27, label: "土壤压力"},
                            {value: 28, label: "土壤PH"}
                        ]
                    }, {
                        value: 29,
                        label: "5号棚",
                        children: [
                            {value: 30, label: "光照强度"},
                            {value: 31, label: "CO2浓度"},
                            {value: 32, label: "空气温度"},
                            {value: 33, label: "土壤湿度"},
                            {value: 34, label: "土壤压力"},
                            {value: 35, label: "土壤PH"}
                        ]
                    }, {
                        value: 36,
                        label: "6号棚",
                        children: [
                            {value: 37, label: "光照强度"},
                            {value: 38, label: "CO2浓度"},
                            {value: 39, label: "空气温度"},
                            {value: 40, label: "土壤湿度"},
                            {value: 41, label: "土壤压力"},
                            {value: 42, label: "土壤PH"}
                        ]
                    }, {
                        value: 43,
                        label: "7号棚",
                        children: [
                            {value: 44, label: "光照强度"},
                            {value: 45, label: "CO2浓度"},
                            {value: 46, label: "空气温度"},
                            {value: 47, label: "土壤湿度"},
                            {value: 48, label: "土壤压力"},
                            {value: 49, label: "土壤PH"}
                        ]
                    },
                ],

                // 是否允许多选
                props: {multiple: true},
            }
        },
        methods: {
            init:function(){
                this.chart=new G2.Chart({
                    container: "lineChart",
                    height: 500,
                    autoFit: true,

                })

                this.chart.scale('light', {
                    min: 0,
                    max: 20,
                    tickCount: 10,
                })
            },

            drawData:function(){
                this.chart.data(this.dataSet)

                this.chart
                    .area()
                    .adjust('stack')
                    .position('date*light')
                    .color('sensorName')

                this.chart
                    .line()
                    .adjust('stack')
                    .position('date*light')
                    .color('sensorName')

                this.chart
                    .point()
                    .adjust('stack')
                    .position('date*light')
                    .color('sensorName')
                    .shape('circle')

                this.chart.render()
            }
        },
        mounted() {
            this.init()
            this.drawData()
        }
    }
</script>

<style scoped>
</style>
