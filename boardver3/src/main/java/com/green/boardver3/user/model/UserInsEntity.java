package com.green.boardver3.user.model;

import lombok.Data;

@Data
public class UserInsEntity {
    private String uid;
    private String upw;
    private String nm;
    private char gender;
    private String addr;
}
