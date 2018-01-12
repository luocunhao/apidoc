package com.pl.hengda.app.service.impl;


import com.pl.hengda.app.mapper.MessageReplyDao;
import com.pl.hengda.app.model.MessageReply;
import com.pl.hengda.app.service.MessageReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageReplyServiceImpl implements MessageReplyService {
    @Autowired
    private MessageReplyDao messageReplyDao;
    @Override
    public List<MessageReply> getMRs() {
        return messageReplyDao.getMRs();
    }

    @Override
    public MessageReply getMRById(int id) {
        return messageReplyDao.getMRById(id);
    }
}
