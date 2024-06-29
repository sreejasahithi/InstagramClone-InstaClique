package com.insta.Instagram_clone.Chat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "files")
public class FileEntity {
    @Id
    private String id;
    private byte[] data;
    private String contentType;

    public FileEntity() {
    }

    public FileEntity(byte[] data, String contentType) {
        this.data = data;
        this.contentType = contentType;
    }

    // Getters and setters
    // Omitted for brevity
}
