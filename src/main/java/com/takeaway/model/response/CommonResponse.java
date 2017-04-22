package com.takeaway.model.response;


import com.takeaway.model.user.User;

public class CommonResponse {

    private String code;

    private String msg;

    public CommonResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
