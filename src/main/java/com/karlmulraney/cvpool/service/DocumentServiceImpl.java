package com.karlmulraney.cvpool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karlmulraney.cvpool.dao.DocumentRepository;
import com.karlmulraney.cvpool.entity.Document;

@Service
public class DocumentServiceImpl implements DocumentService{
    
    private DocumentRepository documentRepository;

    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Autowired
    public DocumentServiceImpl(DocumentRepository theDocumentRepository){

        documentRepository = theDocumentRepository;
    }

    @Override
    public List<Document> findAll(){
        
        return documentRepository.findAllByOrderByUploadTimeDesc();
    }

    @Override
    public Document findById(int theId){
        
        Optional<Document> result = documentRepository.findById(theId);

        Document theDocument = null;

        if(result.isPresent()){
            theDocument = result.get();
        }
        else{
            throw new RuntimeException("Did not find the document id - " + theId);
        }
        return theDocument;
    }

    @Override
    public Document save(Document theDocument){
        return documentRepository.save(theDocument);
    }

    @Override
    public void deleteById(int theId){
        documentRepository.deleteById(theId);
    }
}
