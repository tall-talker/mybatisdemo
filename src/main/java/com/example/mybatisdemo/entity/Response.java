package com.example.mybatisdemo.entity;

//服务端返回值
public class Response {

    //一般返回请求失败的消息
    private String message;
    //请求成功标志位
    private Boolean success;
    //请求中包含的数据
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
