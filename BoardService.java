package com.green.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardMapper MAPPER;

    @Autowired
    public BoardService(BoardMapper mapper) {
        this.MAPPER = mapper;
    }
    public int insBoard(BoardEntity entity){
        System.out.println("service-insBoard");
        MAPPER.insBoard(entity);
        return 1;
    }
}
