package com.green.board7;

import com.green.board7.model.BoardDetailVo;
import com.green.board7.model.BoardDto;
import com.green.board7.model.BoardInsDto;
import com.green.board7.model.BoardVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.ToString;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;

@Tag(name = "게시판", description = "게시판 CRUD")
@RestController
@RequestMapping("/")
public class BoardController {
    private final BoardService SERVICE;
    private final Logger LOGGER;

    @Autowired
    public BoardController(BoardService service) {
        LOGGER = LoggerFactory.getLogger(BoardController.class);
        this.SERVICE = service;
    }

    @PostMapping
    @Operation(summary = "글 등록",description = "글 등록할 수 있습니다.")
    public int postBoard(@RequestBody BoardInsDto dto) {
        LOGGER.warn("경고, 글 등록이 됩니다.");
        return SERVICE.insBoard(dto);
    }

    @PutMapping
    public int putBoard(@RequestBody BoardDto dto) {
        return SERVICE.updBoard(dto);
    }

    @DeleteMapping("/{iboard}")
    public int delBoard(@PathVariable int iboard) {
        BoardDto dto = new BoardDto();
        dto.setIboard(iboard);
        return SERVICE.delBoard(dto);
    }
    @GetMapping
    public List<BoardVo> getBoard() {
        return SERVICE.selBoardAll();
    }

    @GetMapping("/{iboard}")
    public BoardDetailVo selBoardById(@PathVariable int iboard) {
        BoardDto dto = new BoardDto();
        dto.setIboard(iboard);
        LOGGER.debug(dto.toString());
        return SERVICE.selBoardById(dto);
    }

}
