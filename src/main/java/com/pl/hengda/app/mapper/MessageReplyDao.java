package com.pl.hengda.app.mapper;


import com.pl.hengda.app.model.MessageReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageReplyDao {
    public List<MessageReply> getMRs();
    public MessageReply getMRById(@Param("id") int id);
}
