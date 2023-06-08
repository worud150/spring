package com.green.boardver3.user;

import com.green.boardver3.user.model.UserInsEntity;
import com.green.boardver3.user.model.UserLoginDto;
import com.green.boardver3.user.model.UserPatchPicDto;
import com.green.boardver3.user.model.UserUpdPwDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService SERVICE;

    @Autowired
    public UserController(UserService service){
        this.SERVICE = service;
    }

    @PostMapping
    @Operation(summary = "회원가입", description = "" +
            "uid: [20] 회원 아이디,<br>" +
            "upw: [100] 회원 비밀번호,<br>" +
            "nm: [30] 회원명,<br>" +
            "gender: [1] 성별(M: 남성, F: 여성:),<br>" +
            "addr: [100] 대구시 달서구")
    public int postUser (@RequestBody UserInsEntity entity) {
        return SERVICE.postUser(entity);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "" +
            "리턴값: :+" +
            "(1)로그인 성공, "+
            "(2)아이디 없음, "+
            "(3)비밀번호 다름")
    public int postLoginUser(@RequestBody UserLoginDto dto) {
        return SERVICE.login(dto);
    }

    @PatchMapping("/pw")
    public int updPw (@RequestBody UserUpdPwDto dto) {
       return SERVICE.updUserPw(dto);
    }
    @PatchMapping(name="/pic", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public int patchPicUser(@RequestPart MultipartFile pic, @RequestParam int iuser) {
        UserPatchPicDto dto = new UserPatchPicDto();
        dto.setIuser(iuser);
        return SERVICE.updUserPic(pic, dto);
    }
}

