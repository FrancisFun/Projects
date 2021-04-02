/**
 * @Author 范承祥
 * @CreateTime 2020/7/23
 * @UpdateTime 2020/7/23
 */
package com.sosotaxi.driver.model.message;

import com.google.gson.annotations.SerializedName;
import com.sosotaxi.driver.model.message.BaseBody;

/**
 * 更新司机信息响应主体
 */
public class UpdateDriverResponseBody extends BaseBody {
    /**
     * 消息ID
     */
    private long messageId;

    /**
     * 消息
     */
    @SerializedName("msg")
    private String message;

    /**
     * 状态码
     */
    private int statusCode;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
