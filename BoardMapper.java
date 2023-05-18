package com.green.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity entity);
    int updBoard(BoardEntity entity);
    int delBoard(BoardEntity entity);
    List<BoardEntity> selBoardAll();
    BoardEntity selBoardById(BoardEntity entity);
}
