package com.green.board7.fileupload;

import com.green.board7.fileupload.model.FileUploadInsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileupload")
public class FileUploadController {
    private final FileUploadService SERVICE;
    private final Logger LOGGER;

    @Autowired
    public FileUploadController(FileUploadService service) {
        LOGGER = LoggerFactory.getLogger(FileUploadController.class);
        this.SERVICE = service;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
                 MediaType.MULTIPART_FORM_DATA_VALUE})
    public void fileUpload(@RequestPart FileUploadInsDto dto,
                           @RequestPart MultipartFile img) {
        LOGGER.info("imgFileName : " + img.getOriginalFilename());
        SERVICE.fileUpload(img, dto);
    }
}
