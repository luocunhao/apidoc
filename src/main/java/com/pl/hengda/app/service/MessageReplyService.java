package com.pl.hengda.app.service;



import com.pl.hengda.app.model.MessageReply;

import java.util.List;

public interface MessageReplyService {
    public List<MessageReply> getMRs();
    public MessageReply getMRById(int id);
}
