package com.pl.hengda.app.service;



import com.pl.hengda.app.model.Todo;

import java.util.List;


public interface TodoService {
    public List<Todo> getTodoByUserid(String userid, String date);
}
