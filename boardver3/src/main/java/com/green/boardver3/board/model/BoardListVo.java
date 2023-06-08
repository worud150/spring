package com.green.boardver3.board.model;

import lombok.Data;

@Data
public class BoardListVo {
    private int iboard;
    private String title;
    private int iuser;
    private String createdAt;
}
