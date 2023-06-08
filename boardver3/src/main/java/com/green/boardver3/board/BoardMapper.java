package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity dto);
    int selBoardMaxPage();
    BoardDetailVo selBoardDetail(BoardSelDto dto);
    int updBoard(BoardUpdDto dto);
    int delBoard(BoardDelDto dto);
    List<BoardVo> selBoard(BoardSelDto dto);
    //List<BoardListVo> selBoardList(BoardDto dto);

}
