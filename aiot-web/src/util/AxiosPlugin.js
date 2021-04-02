import axios from 'axios'
import { Message } from 'element-ui';

export const Axios = axios.create({
  // baseURL:  'http://127.0.0.1:6060',
  timeout: 10000,
})

//POST传参序列化(添加请求拦截器)
// 在发送请求之前做某件事
Axios.interceptors.request.use(config => {
  if (config.url.indexOf("login") != -1 && config.url.indexOf("register") != -1) {
    return config
  }

  // 下面会说在什么时候存储 token
  if (localStorage.token) {
    config.headers.Authorization = localStorage.token
  }

  return config
},error =>{
  alert("错误的传参", 'fail')
  return Promise.reject(error)
})

// 返回状态判断(添加响应拦截器)
Axios.interceptors.response.use(res =>{
  return res
}, error => {
  if(error.response.status === 401) {
    // 401 说明 token 验证失败
    // 可以直接跳转到登录页面，重新登录获取 token
    location.href = '/auth'
  }else if(error.response.status === 403){
    Message.error("无此操作权限")
  }else if(error.response.status === 500){
    Message.error("服务器异常")
  }
  // 返回 response 里的错误信息
  return Promise.reject(error.response.data)
})

export default {
  install(Vue) {
    Object.defineProperty(Vue.prototype, '$axios', { value: Axios })
  }
}
