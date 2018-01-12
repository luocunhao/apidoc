package com.pl.hengda.app.model;
//自定义消息回复实体类
public class MessageReply {
    private  int id;
    private  String content;

    public MessageReply(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
