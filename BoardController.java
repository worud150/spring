package com.green.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public int boardPost(@RequestBody BoardEntity entity){
        System.out.println(entity);
        return SERVICE.insBoard(entity);
    }
    @PutMapping
    public int boardPut(@RequestBody BoardEntity entity){
        return SERVICE.updBoard(entity);
    }
    @DeleteMapping("/{iboard}")
    public int boardDelete(@PathVariable int iboard){
        BoardEntity entity = new BoardEntity();
        entity.setIboard(iboard);
        return SERVICE.delBoard(entity);
    }
    @GetMapping
    public List<BoardEntity> boardGetAll() {
        return SERVICE.selBoardAll();
    }
    @GetMapping("/{iboard}")
    public BoardEntity boardGetByID(@PathVariable int iboard) {
        BoardEntity entity = new BoardEntity();
        entity.setIboard(iboard);
        return  SERVICE.selBoardById(entity);
    }

}
