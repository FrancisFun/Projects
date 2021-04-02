export default {
  roleTagType,
  roleTag,
  statusTagType,
  statusTag,
  src
}

import globalConfig from "../../globalConfig.js"

function roleTagType(role) {
  switch (role) {
    case "ROLE_SU":
      return ""
    case "ROLE_ADMIN":
      return "success"
    case "ROLE_MONITOR":
      return "warning"
    default:
      return "info"
  }
}

/**
 * 用户标签转换
 * @param rank 用户等级
 */
function roleTag(role) {
  switch (role) {
    case "ROLE_SU":
      return "超级管理员"
    case "ROLE_ADMIN":
      return "管理员"
    case "ROLE_MONITOR":
      return "监督员"
    default:
      return "未知身份"
  }
}

function statusTagType(status) {
  switch (status) {
    case 0:
      return ""
    case 1:
      return "success"
    case 2:
      return "danger"
    default:
      return "info"
  }
}

function statusTag(status) {
  switch (status) {
    case 0:
      return "冻结"
    case 1:
      return "正常"
    case 2:
      return "已注销"
    default:
      return "未知状态"
  }
}

function src(srcUrl){
  if(srcUrl===null||srcUrl===""){
    return ""
  }
  return globalConfig.fileServerHostAndPort + srcUrl
}
