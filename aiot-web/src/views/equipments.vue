<!--
 * @FileDescription: 设备管理页面
 * @Author: 范承祥
 * @Date: 2019/02/17
 * @LastEditors: 范承祥
 * @LastEditTime: 2020/07/31
 -->
<template>
    <el-main
        v-loading.fullscreen="loading"
        class="equipments">
        <el-row :gutter="20">
            <el-col :xs="24" :span="8"
                    v-for="(item,i) in deviceList"
                    :key="i">
                <device-board :id="item.id"
                        :area="item.area.name"
                        :device-id="item.deviceId"
                        :status="item.status"
                        :firmware-version="item.firmwareVersion"
                        :hardware-version="item.hardwareVersion"
                        :software-version="item.softwareVersion"
                        :mac="item.mac"
                        :nodes="item.nodes"
                ></device-board>
            </el-col>
        </el-row>
    </el-main>
</template>

<script>
    import deviceBoard from '../components/boards/device-board.vue'
    let self = null
    export default {
        name: "equipment",
        components:{
            deviceBoard,
        },
        data(){
            return {
                // 设备集
                deviceList: [],
            }
        },
        methods: {
            loadDevices() {
                if(self.$store.state.devices.length !== 0){
                    self.$store.state.devices.forEach(item => {
                        self.deviceList.push(item)
                    })
                    return
                }
                self.$axios.get("/api/device/selectAllByLimit",{
                    params: { offset: 0 }
                })
                .then(res => {
                    self.$store.state.devices = res.data
                    self.$store.state.devices.forEach(item => {
                        self.deviceList.push(item)
                    })
                    console.log(this.$store.state.devices)
                })
                .catch(err => {
                    if(err.status !== 403 && err.status !== 500){
                        this.$message.error(err)
                    }
                })
            }
        },
        created() {
            self = this
            self.loadDevices()
        },
        computed: {
            loading: function(){
                return self.$store.state.devices.length === 0
            }
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
</style>
