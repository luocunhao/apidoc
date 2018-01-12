package com.pl.hengda.app.mapper;


import com.pl.hengda.app.model.Todo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TodoDao {
    public List<Todo> getTodoByUserid(@Param("userid") String userid, @Param("date") String date);
}
