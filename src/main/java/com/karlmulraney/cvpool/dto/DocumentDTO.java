package com.karlmulraney.cvpool.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DocumentDTO {
    
    private MultipartFile file;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String uploaderFirstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String uploaderLastName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String nominatorFirstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String nominatorLastName;
    
    public MultipartFile getFile() {
        return file;
    }
    
    public String getUploaderFirstName() {
        return uploaderFirstName;
    }

    public String getUploaderLastName() {
        return uploaderLastName;
    }

    public String getNominatorFirstName() {
        return nominatorFirstName;
    }

    public String getNominatorLastName() {
        return nominatorLastName;
    }
    
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setUploaderFirstName(String uploaderFirstName) {
        this.uploaderFirstName = uploaderFirstName;
    }

    public void setUploaderLastName(String uploaderLastName) {
        this.uploaderLastName = uploaderLastName;
    }

    public void setNominatorFirstName(String nominatorFirstName) {
        this.nominatorFirstName = nominatorFirstName;
    }

    public void setNominatorLastName(String nominatorLastName) {
        this.nominatorLastName = nominatorLastName;
    }
}
