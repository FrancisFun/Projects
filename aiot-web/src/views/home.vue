<!--
 * @FileDescription: 农场概览页面
 * @Author: 范承祥
 * @Date: 2019/02/17
 * @LastEditors: 范承祥
 * @LastEditTime: 2020/07/31
 -->
<template>
  <el-main class="home"
           v-loading.fullscreen="loading">
    <el-row>
      <el-col>
        <mapBoard>

        </mapBoard>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :xs="24" :span="8"
              v-for="(item,i) in labelList"
              :key="i">
        <info-board
            :title="item.name"
            :src="item.icon"
            :select=item.id
            :unit="item.unit">
        </info-board>
      </el-col>
    </el-row>
  </el-main>
</template>

<script>
  import mapBoard from '../components/boards/map-board.vue'
  import infoBoard from '../components/boards/info-board.vue'

  let self = null
  export default {
    name: "home",
    components: {
      mapBoard,
      infoBoard
    },
    data() {
      return {
        // 标签集
        labelList: []
      }
    },
    computed: {
      loading: function () {
        return self.labelList === 0
      }
    },
    created() {
      self = this

      if (self.$store.state.dataInformation.length !== 0) {
        self.$store.state.dataInformation.forEach(item => {
          self.labelList.push(item)
        })
        return
      }
      self.$axios.get("/api/dataInformation/selectAll")
          .then(res => {
            self.$store.state.dataInformation = res.data
            self.$store.state.dataInformation.forEach(item => {
              self.labelList.push(item)
            })
            console.log("Got data information")
          }).catch(err => {
        if(err.status !== 403 && err.status !== 500){
          this.$message.error(err)
        }
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .el-row {
    margin-bottom: 10px;
  }

  .el-col {
    border-radius: 5px;
    margin-bottom: 10px;
  }
</style>
