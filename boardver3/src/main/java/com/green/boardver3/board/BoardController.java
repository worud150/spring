package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService SERVICE;

    @Autowired
    public BoardController(BoardService service){
        this.SERVICE = service;
    }

    @PostMapping
    public int postBoard(@RequestBody BoardInsDto dto){
        return SERVICE.postBoard(dto);
    }

//    @GetMapping
//    public List<BoardListVo> selBoardList(@RequestParam int page, @RequestParam int row){
//        BoardDto dto = new BoardDto();
//        dto.setPage(page);
//        dto.setRowLen(row);
//        return SERVICE.selBoardList(dto);
//    }

    @GetMapping
    public List<BoardVo> getBoard(@RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "30") int row) {
        BoardSelDto dto = new BoardSelDto();
        dto.setPage(page);
        dto.setRow(row);
        return SERVICE.selBoard(dto);
    }
    @GetMapping("/maxpage")
    public int maxPage(@RequestParam int row){
        return SERVICE.maxPage(row);
    }

    @GetMapping("/{iboard}")
    public Cmt selDetail (@PathVariable int iboard) {
        BoardSelDto dto = new BoardSelDto();
        dto.setIboard(iboard);
        return SERVICE.selBoardDetail(dto);
    }

    @PutMapping
    public int putBoard(@RequestBody BoardUpdDto dto) {
        return SERVICE.upbBoard(dto);
    }

    @DeleteMapping
    public int delBoard(@RequestBody BoardDelDto dto) throws Exception {
        return SERVICE.delBoard(dto);
    }

}
