package com.green.todoapp.model;

import lombok.Data;

@Data
public class TodoSelPageDto {
    private int startIdx;
    private int page;
    private int row;
}
