package com.green.boardver3.cmt;

import com.green.boardver3.cmt.model.*;
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

    @PostMapping("/{iboard}/cmt")
    public int postBoardCmt(@PathVariable int iboard,@RequestBody CmtInsDto dto) {
        System.out.println(iboard);
        System.out.println(dto);
        CmtEntity entity = new CmtEntity();
        entity.setIboard(iboard);
        entity.setIuser(dto.getIuser());
        entity.setCtnt(dto.getCtnt());
        return SERVICE.insBoardCmt(entity);
    }

    @GetMapping("/{iboard}/cmt")
    public CmtRes getBoardCtm(@PathVariable int iboard
                                   , @RequestParam int page
                                    , @RequestParam(defaultValue = "5") int row) {
        CmtSelDto dto = new CmtSelDto();
        dto.setIboard(iboard);
        dto.setPage(page);
        dto.setRow(row);
        return SERVICE.selBoardCmt(dto);

    }

    @DeleteMapping("/cmt/{iboardCmt}")
    public int delBoardCmt(@PathVariable int iboardCmt,
                           @RequestParam int iuser) {
        CmtDelDto dto = new CmtDelDto();
        dto.setIboardCmt(iboardCmt);
        dto.setIuser(iuser);
        return SERVICE.delBoardCmt(dto);
    }

    @PutMapping("/cmt/{iboardCmt}")
    public int putBoardCmt (@PathVariable int iboardCmt,
                            @RequestBody CmtUpdDto dto) {
        CmtEntity entity = new CmtEntity();
        entity.setIboardCmt(iboardCmt);
        entity.setIuser(dto.getIuser());
        entity.setCtnt(dto.getCtnt());
        return SERVICE.updBoardCmt(entity);
    }
}
