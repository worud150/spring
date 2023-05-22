package com.green.board7.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BoardInsDto {
    private String title;
    @Schema(description = "작성자")
    private String content;
    private String writer;
}