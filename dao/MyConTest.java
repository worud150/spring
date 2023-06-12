package com.green.java.dao;

import com.green.java.dao.model.BoardDetailVo;
import com.green.java.dao.model.BoardEntity;
import com.green.java.dao.model.BoardSelDto;
import com.green.java.dao.model.BoardVo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MyConTest {
    public static void main(String[] args) {
       BoardDao dao = new BoardDao();
//
//       BoardEntity entity = new BoardEntity();
//        entity.setTitle("뾰로롱");
//        entity.setCtnt("뾰로롱");
//        entity.setIboard(1032);
//        entity.setIuser(9);

//       int result = dao.insBoard(entity);
//        System.out.println(result);
//
//        int result2 = dao.updBoard(entity);
//        System.out.println(result2);

//        BoardSelDto dto = new BoardSelDto();
//        dto.setIboard(1014);
//        BoardDetailVo result = dao.selBoardDetail(dto);
//        System.out.println("r : " + result);

        BoardSelDto dto = new BoardSelDto();

        dto.setPage(5);
        dto.setRow(30);
        dto.setStartIdx((dto.getPage() - 1) * dto.getRow());

        List<BoardVo> list = dao.selBoard(dto);
        for (BoardVo vo : list ) {
            System.out.println(vo);
        }
    }
}