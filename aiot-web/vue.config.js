const path = require('path');//引入path模块
const globalConfig = require('./globalConfig')

function resolve(dir){
  return path.join(__dirname,dir)//path.join(__dirname)设置绝对路径
}

module.exports = {
  chainWebpack: config => {
    // 添加别名
    config.resolve.alias
        .set("@", resolve("src"))
        .set("@assets", resolve("src/assets"))
  },
  devServer: {
    proxy: {
      '/api': {
        target: globalConfig.hostAndPort,
        ws: true,
        secure: false,
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''  //通过pathRewrite重写地址，将前缀/api转为/
        }
      }
    }
  },
  lintOnSave: false
}
