package com.green.board7.fileupload;

import com.green.board7.fileupload.model.FileEntity;
import com.green.board7.fileupload.model.FileUploadInsDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadService {
    private final FileUploadMapper MAPPER;

    @Autowired
    public FileUploadService (FileUploadMapper mapper) {
        this.MAPPER = mapper;
    }
    @Value("${file.dir}")
    private String fileDir;

    public void fileUpload(MultipartFile img, FileUploadInsDto dto) {
        System.out.println("fileDir : " + fileDir);

        // 원래 파일 이름 추출 => 확장자를 빼내기 위해
        String originFileName = img.getOriginalFilename();

        // 파일이름으로 사용할 uuid 생성 자바유효아이디값
        String uuid = UUID.randomUUID().toString();

        // 확장자명 구하기
        int doxIdx = originFileName.lastIndexOf(".");
        String ext = originFileName.substring(doxIdx);

        // 이름과 확장자만 추출
        String saveFileName = uuid + ext;

        // 전체경로 추출 => 어디에 저장될건까지도 저장함
        String saveFilePath = fileDir + saveFileName;

        File file = new File(saveFilePath);
        try {
            img.transferTo(file);

            FileEntity entity = FileEntity.builder()
                    .path(saveFilePath)
                    .uploader(dto.getUploader())
                    .levelValue(dto.getLevelValue())
                    .build();
            MAPPER.insFile(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
