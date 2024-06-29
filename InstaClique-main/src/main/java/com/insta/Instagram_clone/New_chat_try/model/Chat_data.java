
package com.insta.Instagram_clone.New_chat_try.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.Binary;
import java.time.LocalDateTime;
import java.util.Date;

@Document("Chat_data")
public class Chat_data {
    @Id
    private String id;
    public String from_id;
    public String to_id;
    public Date time; // Use LocalDateTime for representing time
    public Binary content;
    public String data_type;

    // Default constructor
    public Chat_data() {
    }

    public Chat_data(String from_id, String to_id, Date time, Binary content, String data_type) {
        this.from_id = from_id;
        this.to_id = to_id;
        this.time = time;
        this.content = content;
        this.data_type = data_type;
    }

    public Date getTime() {
        return this.time;
    }

}
