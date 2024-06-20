package com.swp391.ims_application.service.imp;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {

    String uploadFile(MultipartFile file) throws IOException;


}
