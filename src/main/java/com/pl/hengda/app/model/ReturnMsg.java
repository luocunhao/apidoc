package com.pl.hengda.app.model;

public class ReturnMsg {
    private String code;
    private Object content;
    private String errorMsg;
    public ReturnMsg(){}
    public ReturnMsg(String code, String content, String errorMsg) {
        this.code = code;
        this.content = content;
        this.errorMsg = errorMsg;
    }

    public String getCode() {
        return code;
    }

    public Object getContent() {
        return content;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ReturnMsg{" +
                "code='" + code + '\'' +
                ", content='" + content + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
