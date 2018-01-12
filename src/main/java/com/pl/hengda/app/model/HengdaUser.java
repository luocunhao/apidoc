package com.pl.hengda.app.model;

import lombok.*;
import org.springframework.stereotype.Service;

@ToString
public class HengdaUser {
    @Getter @Setter  private String userid;
    @Getter @Setter private String username;
    @Getter @Setter private String phone;
    @Getter @Setter private  String channel;
    @Getter @Setter private String consultant;

    public HengdaUser() {
    }

    public HengdaUser(String userid, String username, String phone, String channel, String consultant) {
        this.userid = userid;
        this.username = username;
        this.phone = phone;
        this.channel = channel;
        this.consultant = consultant;
    }

    public HengdaUser(String userid, String username, String phone, String channel) {
        this.userid = userid;
        this.username = username;
        this.phone = phone;
        this.channel = channel;
    }
}
