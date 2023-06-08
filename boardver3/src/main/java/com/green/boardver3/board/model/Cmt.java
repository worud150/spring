package com.green.boardver3.board.model;

import com.green.boardver3.cmt.model.CmtRes;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Cmt {
    private BoardDetailVo vo;
    private CmtRes cmt;
}