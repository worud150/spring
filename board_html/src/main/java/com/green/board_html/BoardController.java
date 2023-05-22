package com.green.board_html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService SERVICE;

    @Autowired
    public BoardController( BoardService service) {
        this.SERVICE = service;
    }

    // show list
    @GetMapping
    public String selBoard (Model model) { // 모델에 값을 저장하고 html에 보내기위헤
        model.addAttribute("boardList",SERVICE.selBoard());
        return "boardList";
    }
    // writer board
    @GetMapping("/writer")
    public String insBoard (){
        return "writer";
    }

    @PostMapping("/writer")
    public String insBoard (Model model, BoardEntity entity){
        SERVICE.insBoard(entity);
        //return selBoard(model);
        return "redirect:/board";
    }
    // Detail list
    @GetMapping("/{iboard}")
    public String boardDT (@PathVariable int iboard, Model model) {
        BoardEntity ent = new BoardEntity();
        ent.setIboard(iboard);
        model.addAttribute("boardList", SERVICE.selBoardDetail(ent));
        return "boardDetail";
    }
    // delete
    @GetMapping("/delete/{iboard}")
    public String delBoard (@PathVariable int iboard, Model model) {
        BoardEntity ent = new BoardEntity();
        ent.setIboard(iboard);
        SERVICE.delBoard(ent);
        return "redirect:/board";
    }
    // update
    @GetMapping("/update/{iboard}")
    public String updBoard (@PathVariable int iboard, Model model) {
        BoardEntity ent = new BoardEntity();
        ent.setIboard(iboard);
        model.addAttribute("update",SERVICE.selBoardDetail(ent));
        return "boardUpDate";
    }
    @PostMapping("/update")
    public String changBoard (BoardEntity entity, Model model) {
        SERVICE.updBoard(entity);
        model.addAttribute("boardList", SERVICE.selBoardDetail(entity));
        return "boardDetail";
    }
}
