package com.springproject.weathersharecommunity.Controller;

import com.springproject.weathersharecommunity.service.S3FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileUploadController {
    private final S3FileUploadService fileUploadService;

    @PostMapping("/imageTest")
    public String uploadImage(@RequestPart MultipartFile file ){
        return fileUploadService.uploadImage(file, "");
    }
}
