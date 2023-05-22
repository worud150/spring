package com.green.board7;

import com.green.board7.model.BoardDetailVo;
import com.green.board7.model.BoardDto;
import com.green.board7.model.BoardInsDto;
import com.green.board7.model.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardInsDto dto);
    int updBoard(BoardDto dto);
    int delBoard(BoardDto dto);
    List<BoardVo> selBoardAll();
    BoardDetailVo selBoardById(BoardDto dto);
}
