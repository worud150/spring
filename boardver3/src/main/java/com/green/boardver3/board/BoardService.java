package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import com.green.boardver3.cmt.CmtMapper;
import com.green.boardver3.cmt.CmtService;
import com.green.boardver3.cmt.model.CmtDelDto;
import com.green.boardver3.cmt.model.CmtRes;
import com.green.boardver3.cmt.model.CmtSelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    private final BoardMapper MAPPER;
    private final CmtService cmtService;

    @Autowired
    public BoardService(BoardMapper mapper, CmtService cmtService){
        this.MAPPER = mapper;
        this.cmtService = cmtService;
    }

//    insert문 작성과 작성 후 내가 작성한 게시글 바로 볼 수 있게 하기
//     방금 작성한 pk값을 얻을 수 있다 = (result)
    public int postBoard(BoardInsDto dto){
        BoardEntity entity = new BoardEntity();
        entity.setTitle(dto.getTitle());
        entity.setCtnt(dto.getCtnt());
        entity.setIuser(dto.getIuser());

        int result = MAPPER.insBoard(entity);

        if (result == 1) {
            return entity.getIboard();
        }

        return result;
    }

//    원래 우리끼리 작성했던 코드
//    public List<BoardListVo> selBoardList (BoardDto dto){
//        dto.setStartIdx((dto.getPage()-1) * dto.getRowLen());
//        return MAPPER.selBoardList(dto);
//    }

//    선생님과 함께 작성한 코드 (각자 비교해보기)
//    전체 게시글 select하기 쿼리스트링으로 작성함
    public List<BoardVo> selBoard(BoardSelDto dto) {
        int startIdx = (dto.getPage() - 1) * dto.getRow();
        dto.setStartIdx(startIdx);
        return MAPPER.selBoard(dto);
    }
//    내가 갖고있는 DB의 최대 페이지값 리턴하기
    public int maxPage(int row) {
        int count = MAPPER.selBoardMaxPage();
        return (int)Math.ceil((double) count / row);
    }
    int page = 1;
    int row = 5;
//    게시글전체 중 하나만 디테일로 보기 ctnt 포함 됨
    public Cmt selBoardDetail(BoardSelDto dto) {
        BoardDetailVo vo = MAPPER.selBoardDetail(dto);
        CmtSelDto selDto = new CmtSelDto();
        selDto.setRow(row);
        selDto.setPage(page);
        selDto.setIboard(dto.getIboard());
        CmtRes cmt = cmtService.selBoardCmt(selDto);
        return Cmt.builder()
                .vo(vo)
                .cmt(cmt)
                .build();
    }
//   update문 작성하기
    public int upbBoard(BoardUpdDto dto) {
        return MAPPER.updBoard(dto);
    }

//   delete문 작성하기
    @Transactional(rollbackFor = Exception.class)
    public int delBoard(BoardDelDto dto) throws Exception {

        CmtDelDto cmtDto = new CmtDelDto();
        cmtDto.setIboard(dto.getIboard());
        cmtService.delBoardCmt(cmtDto);
        // 그 글에 달려있는 댓글을 전부 삭제해야함.
        int result = 0;
        result = MAPPER.delBoard(dto);
        if (result == 0) {
            throw new Exception("삭제 권한 없음");
        }
        return result;
    }


}
