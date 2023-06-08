package com.green.boardver3.user;

import com.green.boardver3.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserInsEntity entity);
    UserLoginVo selUserByUid(UserLoginDto dto);
    int updPw (UserUpdPwDto dto);
    int updUserPic (UserPatchPicDto dto);
}
