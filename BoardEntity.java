package com.green.board;

import lombok.Data;

@Data
public class BoardEntity {
    private int iboard;
    private String title;
    private String ctnt;
    private String writer;
    private String createdAt;
    private String updateAt;
}
