import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userInfo: {},
    dataInformation: [],
    moduleInformation: [],
    devices: [],
    users: [],
    host: "127.0.0.1:6060",
    isLoading: false
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
