package com.insta.Instagram_clone.Chat.model;

// import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document("chats")
public class Chat_each {
    @Id
    private String id;
    String to_id;
    String from_id;
    Content content;

    public Chat_each(String to_id, String from_id, Content content) {
        this.to_id = to_id;
        this.from_id = from_id;
        this.content = content;
    }

    public String get_to_id() {
        return to_id;
    }

    public String get_from_id() {
        return from_id;
    }

    public Content get_content() {
        return content;
    }
}

// Define a Content Stringerface