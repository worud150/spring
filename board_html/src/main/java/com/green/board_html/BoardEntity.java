package com.green.board_html;

import lombok.Data;

@Data
public class BoardEntity {
    private int iboard;
    private String title;
    private String content;
    private String writer;
    private String createdAt;
    private String updatedAt;
}
