package com.green.todoapp;

import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoSelVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({ TodoService.class })
class TodoServiceTest {

    @MockBean
    private TodoMapper mapper;

    @Autowired
    private  TodoService service;


    @Test
    void postTodo() {
        TodoEntity entity = new TodoEntity();
        entity.setCtnt("내용입력");
        when(mapper.insTodo(entity)).thenReturn(1);// 테스트하기위함 그저 리턴 1 해 억지로 말하기

        TodoInsDto dto = new TodoInsDto();
        dto.setCtnt("내용입력");
        int result = service.postTodo(dto);

        assertEquals(0,result);

        verify(mapper).insTodo(any(TodoEntity.class));
    }

    @Test
    @DisplayName("TodoService - Bring Todo List")
    void selTodo() {
        List<TodoSelVo> mockList = new ArrayList<>();

        mockList.add(new TodoSelVo(1,"테스트","2023",null,1,"2023-10-5"));
        mockList.add(new TodoSelVo(2,"테스트2","20232","df.jpg",0,"2023-10-5"));
        mockList.add(new TodoSelVo(3,"테스트3","20233","null.png",1,"2023-10-5"));
        mockList.add(new TodoSelVo(4,"테스트4","20234","null.psd",0,"2023-10-5"));

        when(mapper.selTodo()).thenReturn(mockList);

        List<TodoSelVo> list =  service.selTodo();

        assertEquals(list, mockList);

        verify(mapper).selTodo();

    }
}