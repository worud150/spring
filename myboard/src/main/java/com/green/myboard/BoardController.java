package com.green.myboard;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/myboard")
public class BoardController {
    private final BoardService SERVICE;

    @Autowired
    public BoardController (BoardService service) {
        this.SERVICE = service;
    }

    @PostMapping
    public int insBoard(@RequestBody BoardEntity entity) {
        return SERVICE.insBoard(entity);
    }
    @PutMapping
    public int updboard(@RequestBody BoardEntity entity){
        return SERVICE.updBoard(entity);
    }
    @DeleteMapping("/{iboard}")
    public int delBoard(@PathVariable int iboard) {
        BoardEntity entity = new BoardEntity();
        entity.setIboard(iboard);
        return SERVICE.delBoard(entity);
    }
    @GetMapping
    public List<BoardEntity> boardGetAll() {
        return SERVICE.selBoardAll();
    }
    @GetMapping ("/{iboard}")
    public BoardEntity selBoardById (@PathVariable int iboard){
        BoardEntity entity = new BoardEntity();
        entity.setIboard(iboard);
        return SERVICE.selBoardById(entity);
    }
}
