package com.green.board_html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BoardService {
    private final BoardMapper MAPPER;

    @Autowired
    public BoardService(BoardMapper mapper){
        this.MAPPER = mapper;
    }

    public int insBoard ( BoardEntity entity) {
        return MAPPER.insBoard(entity);
    }
    public int updBoard ( BoardEntity entity) {
        return MAPPER.updBoard(entity);
    }
    public int delBoard (BoardEntity entity) {
        return MAPPER.delBoard(entity);
    }
    public List<BoardEntity> selBoard () {
        return MAPPER.selBoard();
    }
    public BoardEntity selBoardDetail(BoardEntity entity) {
        return MAPPER.selBoardDetail(entity);
    }
}
