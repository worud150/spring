package com.green.board7.cmt.model;

import lombok.Data;

@Data
public class BoardCmtInsDto {
    private int iboard;
    private String content;
    private String writer;
    private String pw;
}
