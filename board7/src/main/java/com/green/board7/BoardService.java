package com.green.board7;

import com.green.board7.model.BoardDetailVo;
import com.green.board7.model.BoardDto;
import com.green.board7.model.BoardInsDto;
import com.green.board7.model.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardMapper MAPPER;

    @Autowired
    public BoardService(BoardMapper mapper){
        this.MAPPER = mapper;
    }

    public int insBoard(BoardInsDto dto){
        return MAPPER.insBoard(dto);
    }
    public int updBoard(BoardDto dto){
        return MAPPER.updBoard(dto);
    }
    public int delBoard(BoardDto dto) {
        return MAPPER.delBoard(dto);
    }
    public List<BoardVo> selBoardAll() {
        return MAPPER.selBoardAll();
    }
    public BoardDetailVo selBoardById(BoardDto dto) {
        return MAPPER.selBoardById(dto);
    }
}
