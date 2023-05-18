package com.green.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class BoardService {
    private final BoardMapper MAPPER;

    @Autowired
    public BoardService(BoardMapper mapper) {
        this.MAPPER = mapper;
    }
    public int insBoard(BoardEntity entity){
        return MAPPER.insBoard(entity);
    }
    public int updBoard (BoardEntity entity) {
        return  MAPPER.updBoard(entity);
    }
    public int delBoard (BoardEntity entity) {
        return MAPPER.delBoard(entity);
    }
    public List<BoardEntity> selBoardAll(){
        return MAPPER.selBoardAll();
    }
    public BoardEntity selBoardById(BoardEntity entity){
        return MAPPER.selBoardById(entity);
    }
}
