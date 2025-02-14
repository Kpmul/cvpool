package com.karlmulraney.cvpool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.karlmulraney.cvpool.entity.Document;

@Service
public interface DocumentService {
    
    List<Document> findAll();

    Document findById(int theId);

    Document save(Document theDocument);

    void deleteById(int theId);
}
