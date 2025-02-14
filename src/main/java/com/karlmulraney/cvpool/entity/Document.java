package com.karlmulraney.cvpool.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "document")
public class Document {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "uploader_first_name")
    private String uploaderFirstName;

    @Column(name = "uploader_last_name")
    private String uploaderLastName;

    @Column(name = "nominator_first_name")
    private String nominatorFirstName;

    @Column(name = "nominator_last_name")
    private String nominatorLastName;

    @Column(name = "upload_time")
    private LocalDateTime uploadTime;

    public Document() {
    }

    public Document(String thefilePath, String theFileName, String theFileType, String theUploaderFirstName, 
                    String theUploaderLastName, String theNominatorFirstName, String theNominatorLastName,
                    LocalDateTime theuploadTime){
        
        filePath = thefilePath;                
        fileName = theFileName;
        fileType = theFileType;
        uploaderFirstName = theUploaderFirstName;
        uploaderLastName = theUploaderLastName;
        nominatorFirstName = theNominatorFirstName;
        nominatorLastName = theNominatorLastName;
        uploadTime = theuploadTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int theId) {
        id = theId;
    }

    public String getfilePath() {
        return filePath;
    }

    public void setfilePath(String thefilePath){
        filePath = thefilePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String theFileName) {
        fileName = theFileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String theFileType) {
        fileType = theFileType;
    }

    public String getUploaderFirstName() {
        return uploaderFirstName;
    }

    public void setUploaderFirstName(String theUploaderFirstName) {
        uploaderFirstName = theUploaderFirstName;
    }

    public String getUploaderLastName() {
        return uploaderLastName;
    }

    public void setUploaderLastName(String theUploaderLastName) {
        uploaderLastName = theUploaderLastName;
    }

    public String getNominatorFirstName() {
        return nominatorFirstName;
    }

    public void setNominatorFirstName(String theNominatorFirstName) {
        nominatorFirstName = theNominatorFirstName;
    }

    public String getNominatorLastName() {
        return nominatorLastName;
    }

    public void setNominatorLastName(String theNominatorLastName) {
        nominatorLastName = theNominatorLastName;
    }

    public LocalDateTime getuploadTime() {
        return uploadTime;
    }

    public void setuploadTime(LocalDateTime theUploadTime) {
        uploadTime = theUploadTime;
    }

    @Override
    public String toString() {
        return "Document [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + ", uploaderFirstName="
                + uploaderFirstName + ", uploaderLastName=" + uploaderLastName + ", nominatorFirstName="
                + nominatorFirstName + ", nominatorLastName=" + nominatorLastName + ", uploadTime=" + uploadTime 
                + "]";
    }
}
