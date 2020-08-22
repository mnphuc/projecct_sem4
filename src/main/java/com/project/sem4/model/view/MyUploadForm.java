package com.project.sem4.model.view;

import org.springframework.web.multipart.MultipartFile;

public class MyUploadForm {
    private String description;

    // Upload files.
    private MultipartFile[] fileDatas;
}
