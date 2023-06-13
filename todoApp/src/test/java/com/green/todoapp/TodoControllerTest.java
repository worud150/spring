package com.green.todoapp;

import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoSelVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class) // 어떤 클래스를 테스트 할지
class TodoControllerTest {

    @Autowired // 오토와일드는 자체 클래스도 빈등록이 되어야한다.
    private MockMvc mvc; // 스웨거에서 요청보내는 역할을 테스트단에서 하는 친구 코드가 소스를 테스트 하는 것

    @MockBean
    private TodoService service; // service를 가짜로 목업한 상태의 코드



    @Test
    @DisplayName("TODO - 등록")
    void postTodo() throws Exception {
        // 기동시켰을때 지금 컨트롤러만 객체화 한것이기에 만약 제대로 작성이 됐으면 3을 리턴한다
        // given 단계 > 세팅 단계
        given(service.postTodo(any(TodoInsDto.class))).willReturn(3); // 메소드 명만 적어도 임포트 가능함

        // when > 실제실행단계
        String json = "{ \"ctnt\" : \" 테스트 해보기\" }";
        ResultActions ra = mvc.perform(post("/api/todo")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));

        // then 검증
        ra.andExpect(status().isOk())
                .andExpect(content().string("3"))
                .andDo(print());
        // 실행됐는지 확인 하는 역할
        verify(service).postTodo(any());
    }


    @Test
    @DisplayName("TODO - 리스트")
    void getTodo() throws Exception {
        List<TodoSelVo> mockList = new ArrayList<>();

        // 가짜 정보가 필요하다.
        mockList.add(new TodoSelVo(1,"테스트","2023","null",1,"2023-10-5"));
        mockList.add(new TodoSelVo(2,"테스트2","20232","df.jpg",0,"2023-10-5"));
        mockList.add(new TodoSelVo(3,"테스트3","20233","null.png",1,"2023-10-5"));
        mockList.add(new TodoSelVo(4,"테스트4","20234","null.psd",0,"2023-10-5"));

        given(service.selTodo()).willReturn(mockList);

        ResultActions ra = mvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(mockList.size())))
                .andExpect(jsonPath("$[*].itodo").exists())
                .andExpect(jsonPath("$[0].itodo").value(1))
                .andExpect(jsonPath("$[0].ctnt").value("테스트"))
                .andDo(print());

        verify(service).selTodo();
    }
}