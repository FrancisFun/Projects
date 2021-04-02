import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './assets/css/fullscreen.css'
import 'element-ui/lib/theme-chalk/display.css';
import '../theme/index.css'

Vue.config.productionTip = false

// Axios
import AxiosPlugin from './util/AxiosPlugin'
Vue.use(AxiosPlugin)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
