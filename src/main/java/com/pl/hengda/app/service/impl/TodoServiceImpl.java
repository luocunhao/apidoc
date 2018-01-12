package com.pl.hengda.app.service.impl;


import com.pl.hengda.app.mapper.TodoDao;
import com.pl.hengda.app.model.Todo;
import com.pl.hengda.app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoDao todoDao;
    @Override
    public List<Todo> getTodoByUserid(String userid, String date) {
        return todoDao.getTodoByUserid(userid,date);
    }
}
