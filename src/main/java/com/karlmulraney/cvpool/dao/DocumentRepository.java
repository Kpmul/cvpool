package com.karlmulraney.cvpool.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karlmulraney.cvpool.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    
    public List<Document> findAllByOrderByUploadTimeDesc();
}
