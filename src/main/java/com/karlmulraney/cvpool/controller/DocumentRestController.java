package com.karlmulraney.cvpool.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.multipart.MultipartFile;

import com.karlmulraney.cvpool.dto.DocumentDTO;
import com.karlmulraney.cvpool.entity.Document;
import com.karlmulraney.cvpool.service.DocumentService;

import jakarta.validation.Valid;

@Controller
// @RequestMapping("/document")
public class DocumentRestController {

    private final DocumentService documentService;

    private static final String STORAGE_DIRECTORY = System.getProperty("user.home") + "/Desktop/cvpool-files";

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); // true trims whitespace down to null

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    public DocumentRestController(DocumentService theDocumentService){
        documentService = theDocumentService;
    }

    // Mappings -----------------------------------------------------

    @GetMapping("/")
    public String redirectToHome(){
        return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String showHome(Model model){

        model.addAttribute("theDate", java.time.LocalDateTime.now());
        model.addAttribute("pageType", "full");
        
        return "home";
    }
    
    @GetMapping("/all")
    public String findAll(Model model){
        List<Document> documents = documentService.findAll();
        model.addAttribute("documents", documents);
        return "documents-all";
    }

    @GetMapping("/{documentId}")
    public String findById(@PathVariable int documentId, Model model){
        Document theDocument = documentService.findById(documentId);

        if(theDocument == null){
            model.addAttribute("error", "Document id - : " + documentId + " not found");
        }

        return "upload";
    }
// TODO 
// Make sure if something fails - document is deleted from folder too
// Check for duplicate names maybe? 
    @PostMapping("/upload")
    public String uploadDocument(
                    @Valid @ModelAttribute DocumentDTO documentDTO,
                    BindingResult theBindingResult, Model model){

        if(theBindingResult.hasErrors()){
            model.addAttribute("error", "Field missing");
            return "upload";
        }

        try{
            File directory = new File(STORAGE_DIRECTORY);
            if(!directory.exists()){
                directory.mkdirs();
            }

            MultipartFile file = documentDTO.getFile();
            if(file == null){
                model.addAttribute("error", "File needed for upload");
                return "upload";
            }

            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.') +1);
            String baseName = originalFileName.replace("." + fileExtension, "");
            String uniqueFileName = UUID.randomUUID().toString() + "_" + baseName + "." + fileExtension;
            String filePath = STORAGE_DIRECTORY + "/" + uniqueFileName;
            
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            Document document = new Document(
                filePath, baseName, fileExtension,
                documentDTO.getUploaderFirstName(),
                documentDTO.getUploaderLastName(),
                documentDTO.getNominatorFirstName(),
                documentDTO.getNominatorLastName(),
                LocalDateTime.now()
            );
            documentService.save(document);

            model.addAttribute("message", "File uploaded successfully!");
            return "upload-success";
        }catch(Exception e){
            model.addAttribute("error", "Upload failed: " + e.getMessage());
            return "upload";
        }
    }

    @PutMapping("/{id}")
    public String updateDocument(
                            @PathVariable int id,@Valid 
                            @ModelAttribute DocumentDTO theDocumentDTO,
                            BindingResult theBindingResult, Model model){

        if(theBindingResult.hasErrors()){
            model.addAttribute("error","Validation Failed");
            return "upload";
        }

        Document dbDoc = documentService.findById(id);
        if(dbDoc == null){
            model.addAttribute("error", "Document id : " + id + " not found");
            return "upload";
        }

        dbDoc.setUploaderFirstName(theDocumentDTO.getUploaderFirstName());
        dbDoc.setUploaderLastName(theDocumentDTO.getUploaderLastName());
        dbDoc.setNominatorFirstName(theDocumentDTO.getNominatorFirstName());
        dbDoc.setNominatorLastName(theDocumentDTO.getNominatorLastName());

        documentService.save(dbDoc);

        model.addAttribute("messgae", "Document updates successfully");
        return "update-success";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id, Model model){
        
        Document tempDoc = documentService.findById(id);

        if(tempDoc == null){
            model.addAttribute("error", "Document id - " + id + " not found");
            return "upload";
        }

        File file = new File(tempDoc.getfilePath());

        if(file.exists() && file.isFile()){
            file.delete();
        }

        documentService.deleteById(id);

        return "delete-success";
    }
}
