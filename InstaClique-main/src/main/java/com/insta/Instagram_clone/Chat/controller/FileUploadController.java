package com.insta.Instagram_clone.Chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.insta.Instagram_clone.Chat.repository.*;
import com.insta.Instagram_clone.Chat.model.*;
// import com.insta.Instagram_clone.Chat.model.Chat_each.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import javax.swing.text.StringContent;

@RestController
@RequestMapping("/api")

public class FileUploadController {

    private final ChatRepository repo;

    @Autowired
    public FileUploadController(ChatRepository repo) {
        this.repo = repo;
    }
    // @PostMapping("/upload")
    // public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile
    // file) {
    // try {
    // FileEntity fileEntity = new FileEntity(file.getBytes(),
    // file.getContentType());
    // fileRepository.save(fileEntity);
    // return ResponseEntity.ok("File uploaded successfully");
    // } catch (Exception e) {
    // return ResponseEntity.status(500).body("Error uploading file");
    // }
    // }

    @PostMapping("/messages")
    public String handleMessage(@RequestBody MessageRequest request) {
        // Extract content and type from the request
        String to_id = "1";
        String from_id = "2";
        String content = request.content;
        String type = request.type;
        Content c;
        System.out.println(type + content);
        // Check if the content is an instance of Image
        if (type.equals("image")) {
            c = new Image(content);

        } else if (type.equals("audio")) {
            c = new Audio(content);

        } else if (type.equals("text")) {
            c = new Text(content);

        } else {
            return "Unsupported content type";
        }
        Chat_each chat = new Chat_each(to_id, from_id, c);
        repo.save(chat);
        return "Success";
    }

    // @GetMapping("/images")
    // public ResponseEntity<List<FileEntity>> getAllImages() {
    // List<FileEntity> images = fileRepository.findAll();
    // return ResponseEntity.ok().body(images);
    // }

}
