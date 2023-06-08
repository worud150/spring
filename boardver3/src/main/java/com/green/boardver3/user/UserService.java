package com.green.boardver3.user;

import com.green.boardver3.user.model.*;
import com.green.boardver3.utils.CommonUtils;
import com.green.boardver3.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.green.boardver3.utils.FileUtils.makeRandomFileNm;

@Service
public class UserService {
    private final UserMapper MAPPER;
    private final CommonUtils commonUtils;

    @Value("${file.dir}")
    private String fileDir;

    @Autowired
    public UserService(UserMapper mapper, CommonUtils commonUtils) {
        this.MAPPER = mapper;
        this.commonUtils = commonUtils;
    }

    public int postUser(UserInsEntity entity) {
        // 성별 대문자 변경
        char gender = Character.toUpperCase(entity.getGender());
        if (!(gender == 'M' || gender == 'F')) {
            return -1;
        }
        entity.setGender(gender);
        //비밀번호 암호화
        String hashPw = commonUtils.encodeSha256(entity.getUpw());
        entity.setUpw(hashPw);

        // 서버가 터지지 않고 예외잡기
//        try{
//            return MAPPER.insUser(entity);
//        } catch (Exception e){
//            e.printStackTrace();
//            return 0;
//        }
        return MAPPER.insUser(entity);

    }

    public int login(UserLoginDto dto) {
        UserLoginVo vo = MAPPER.selUserByUid(dto);
        if(vo == null) { return 2; }
        String hashedPw = commonUtils.encodeSha256(dto.getUpw());
       if(vo.getUpw().equals(hashedPw)) {
           return 1;
       }
        return 3;
    }

    public int updUserPw(UserUpdPwDto dto) {
        String hashedPw = commonUtils.encodeSha256(dto.getUpw());
        dto.setUpw(hashedPw);
        return MAPPER.updPw(dto);
    }

    public int updUserPic(MultipartFile pic , UserPatchPicDto dto) {
        String centerPath = String.format("user/%d", dto.getIuser());
        String dicPath = String.format("%s/%s", fileDir, centerPath);

        File dic = new File(dicPath);
        if(!dic.exists()) {
            dic.mkdirs();
        }

        String originFileName = pic.getOriginalFilename();
        String savedFileName = FileUtils.makeRandomFileNm(originFileName);
        String savedFilePath = String.format("%s/%s", centerPath, savedFileName);
        String targetPath = String.format("%s/%s", fileDir, savedFilePath);
        File target = new File(targetPath);
        try {
            pic.transferTo(target);
        }catch (Exception e) {
            return 0;
        }
        dto.setMainPic(savedFilePath);
        try {
            int result = MAPPER.updUserPic(dto);
            if(result == 0) {
                throw new Exception("프로필 사진을 등록할 수 없습니다.");
            }
        } catch (Exception e) {
            //파일 삭제
            target.delete();
            return 0;
        }
        return 1;
    }
}
