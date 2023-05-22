package com.green.board7.model;

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
