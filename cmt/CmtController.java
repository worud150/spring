package com.green.board7.cmt;

import com.green.board7.cmt.model.BoardCmtDelDto;
import com.green.board7.cmt.model.BoardCmtDto;
import com.green.board7.cmt.model.BoardCmtInsDto;
import com.green.board7.cmt.model.BoardCmtVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class CmtController {
    private final CmtService SERVICE;

    @Autowired
    public CmtController(CmtService service) {
        this.SERVICE = service;
    }
    @PostMapping("/cmt")
    public int insBoardCmt(@RequestBody BoardCmtInsDto dto) {
        return SERVICE.insBoardCmt(dto);
    }

    @DeleteMapping("/cmt")
    public int deleteBoardCmt(BoardCmtDelDto dto) {
        return SERVICE.delBoardCmt(dto);
    }

    @GetMapping("/{iboard}/cmt")
    public List<BoardCmtVo> getBoardCmt(@PathVariable int iboard){
        BoardCmtDto dto = new BoardCmtDto();
        dto.setIboard(iboard);
        return SERVICE.selBoardCmt(dto);
    }
}
