package com.green.board7.board;

import com.green.board7.board.model.BoardDetailVo;
import com.green.board7.board.model.BoardDto;
import com.green.board7.board.model.BoardInsDto;
import com.green.board7.board.model.BoardVo;
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
    public List<BoardVo> selBoardAll(BoardDto dto) {
        dto.setStatIdx((dto.getPage() - 1 ) * dto.getRowLen());
        return MAPPER.selBoardAll(dto);
    }
    public BoardDetailVo selBoardById(BoardDto dto) {
        return MAPPER.selBoardById(dto);
    }
}
