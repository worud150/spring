package com.green.todoapp;

import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoFinishDto;
import com.green.todoapp.model.TodoSelPageDto;
import com.green.todoapp.model.TodoSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    int insTodo (TodoEntity entity);
    List<TodoSelVo> selTodoPage(TodoSelPageDto dto);
    List<TodoSelVo> selTodo();
    int finishTodo(TodoFinishDto dto);
}
