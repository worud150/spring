package com.green.board7.cmt;

import com.green.board7.cmt.model.BoardCmtDelDto;
import com.green.board7.cmt.model.BoardCmtDto;
import com.green.board7.cmt.model.BoardCmtInsDto;
import com.green.board7.cmt.model.BoardCmtVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CmtService {
    private final CmtMapper MAPPER;

    @Autowired
    public CmtService(CmtMapper mapper) {
        this.MAPPER = mapper;
    }

    public int insBoardCmt (BoardCmtInsDto dto) {
        return MAPPER.insBoardCmt(dto);
    }

    public int delBoardCmt(BoardCmtDelDto dto) {
       return MAPPER.delBoardCmt(dto);
    }

    public List<BoardCmtVo> selBoardCmt(BoardCmtDto dto){
        return MAPPER.selBoardCmt(dto);
    }
}
