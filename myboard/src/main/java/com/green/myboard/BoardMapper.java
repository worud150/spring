package com.green.myboard;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity entity);
    int updBoard(BoardEntity entity);
    int delBoard(BoardEntity entity);
    List<BoardEntity> selBoardAll();
    BoardEntity selBoardById(BoardEntity entity);
}
