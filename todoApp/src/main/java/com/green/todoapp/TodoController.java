package com.green.todoapp;

import com.green.todoapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService SERVICE;

    @Autowired
    public TodoController(TodoService service) {
        this.SERVICE = service;
    }

    @PostMapping
    public int postTodo(@RequestBody TodoInsDto dto) {
        return SERVICE.postTodo(dto);
    }
    @GetMapping
    public List<TodoSelVo> selTodo(){
        return SERVICE.selTodo();
    }

    @GetMapping("/todopage")
    public List<TodoSelVo> selTodoPage( @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "30") int row) {
        TodoSelPageDto dto = new TodoSelPageDto();
        dto.setRow(row);
        dto.setPage(page);
        return SERVICE.selTodoPage(dto);
    }

    @PatchMapping("/{itodo}")
    public int finishTodo(@PathVariable int itodo) {
        TodoFinishDto dto = new TodoFinishDto();
        dto.setItodo(itodo);
        return SERVICE.finTodo(dto);
    }
}
