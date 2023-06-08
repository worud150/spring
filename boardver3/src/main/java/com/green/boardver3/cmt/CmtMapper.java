package com.green.boardver3.cmt;

import com.green.boardver3.cmt.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmtMapper {
    int insBoardCmt(CmtEntity entity);
    List<CmtVo> selBoardCmt(CmtSelDto dto);
    int delBoardCmt(CmtDelDto dto);
    int updBoardCmt(CmtEntity entity);

    int selMaxCmt(int row);

}
