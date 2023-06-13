package com.green.todoapp;

import com.green.todoapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoMapper MAPPER;

    @Autowired

    public TodoService(TodoMapper mapper) {
        this.MAPPER = mapper;
    }

    public int postTodo (TodoInsDto dto) {
        TodoEntity entity = new TodoEntity();
        entity.setCtnt(dto.getCtnt());

        int result = MAPPER.insTodo(entity);
        if (result == 1) {
            return entity.getItodo();
        }
        return result;
    }
    public List<TodoSelVo> selTodo(){
        return MAPPER.selTodo();
    }
    public List<TodoSelVo> selTodoPage(TodoSelPageDto dto){
        int startIdx = (dto.getPage() - 1) * dto.getRow();
        dto.setStartIdx(startIdx);
        return MAPPER.selTodoPage(dto);
    }

    public int finTodo(TodoFinishDto dto) {
        return MAPPER.finishTodo(dto);
    }
}
