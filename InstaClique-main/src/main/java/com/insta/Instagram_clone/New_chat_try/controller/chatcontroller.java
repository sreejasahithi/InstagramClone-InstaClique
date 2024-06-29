package com.insta.Instagram_clone.New_chat_try.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.insta.Instagram_clone.Chat.service.ChatService;
import com.insta.Instagram_clone.New_chat_try.model.*;
import org.bson.types.Binary;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import com.insta.Instagram_clone.New_chat_try.model.*;
import java.time.LocalDateTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Date;
// import java.util.Date;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.io.IOException;
import org.bson.types.Binary;
import java.util.Base64;
import java.time.LocalDateTime;
import com.insta.Instagram_clone.service.*;
import com.insta.Instagram_clone.New_chat_try.service.*;

@RestController
@RequestMapping("/api")
public class chatcontroller {

    private final MongoTemplate mongoTemplate;
    // Singleton.getInstance().getUsername()
    String USER_NAME = Singleton.getInstance().getUsername();

    @Autowired
    public chatcontroller(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    private chatservice chatservice;

    @GetMapping("/chat/{to_id}/{from_id}")
    public ResponseEntity<List<Chat_data>> getChatDataSortedByTimestamp(@PathVariable("to_id") String to_id,
            @PathVariable("from_id") String from_id1) {
        // try {
        // String from_id = Singleton.getInstance().getUsername();
        // // Fetch chat data sorted by timestamp in ascending order
        // // Query for documents where from_id is from_id and to_id is to_id
        // Query query1 = new
        // Query(Criteria.where("from_id").is(from_id).and("to_id").is(to_id));

        // // Query for documents where from_id is to_id and to_id is from_id
        // Query query2 = new
        // Query(Criteria.where("from_id").is(to_id).and("to_id").is(from_id));

        // // Execute the first query
        // List<Chat_data> chatDataList1 = mongoTemplate.find(
        // query1.with(Sort.by(Sort.Direction.ASC, "time")),
        // Chat_data.class);

        // // Execute the second query
        // List<Chat_data> chatDataList2 = mongoTemplate.find(
        // query2.with(Sort.by(Sort.Direction.ASC, "time")),
        // Chat_data.class);

        // // Concatenate the resulting lists
        // List<Chat_data> ChatDataList = new ArrayList<>(chatDataList1);
        // ChatDataList.addAll(chatDataList2);

        // // Sort the combined list based on ascending order of time
        // Collections.sort(ChatDataList, Comparator.comparing(Chat_data::getTime));

        // if (ChatDataList.isEmpty()) {
        // return ResponseEntity.notFound().build(); // Return 404 if no data found
        // }

        // return ResponseEntity.ok(ChatDataList);
        // } catch (Exception e) {
        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); //
        // Handle internal server error
        // }

        List<Chat_data> ChatDataList = chatservice.getChatsorted(to_id, from_id1);

        if (ChatDataList.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no data found
        }
        try {
            return ResponseEntity.ok(ChatDataList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Handle internal server error
        }
    }

    @GetMapping("/chatlist/{from_id}")
    public ResponseEntity<List<String>> getChatlist(@PathVariable("from_id") String from_id) {
        // try {
        // String USER_NAME = Singleton.getInstance().getUsername();

        // // Fetch chat data sorted by timestamp in ascending order
        // System.out.println("IN FUNCTION");
        // System.out.println(USER_NAME);
        // Query query = new Query(Criteria.where("userID").is(USER_NAME));
        // List<ChatList> chatDataList = mongoTemplate.find(query, ChatList.class);

        // System.out.println("CHATLIST");
        // // System.out.println(USER_NAME);

        // // Check if data exists
        // if (chatDataList.isEmpty()) {
        // return ResponseEntity.notFound().build(); // Return 404 if no data found
        // }
        // System.out.println(chatDataList.get(0).chatlist);
        // return ResponseEntity.ok(chatDataList.get(0).chatlist);
        // } catch (Exception e) {
        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        // // Handle internal server error
        // }

        try {
            String USER_NAME = Singleton.getInstance().getUsername();

            List<String> chatDataList = chatservice.getChatlist(USER_NAME);

            // Check if data exists

            System.out.println(chatDataList);
            return ResponseEntity.ok(chatDataList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

            // Handle internal server error
        }
    }

    @GetMapping("/chatlist/{to_id}/{from_id}")
    public ResponseEntity<String> updateChatlist(@PathVariable("from_id") String from_id1,
            @PathVariable("to_id") String to_id) {
        // try {
        // String from_id = Singleton.getInstance().getUsername();
        // // Fetch chat data sorted by timestamp in ascending order
        // Query query = new Query(Criteria.where("userID").is(from_id));
        // System.out.println("UPDATE: " + from_id + " " + to_id);
        // List<ChatList> chatDataList = mongoTemplate.find(query, ChatList.class);
        // ChatList temp = new ChatList(from_id, chatDataList.get(0).chatlist);
        // temp.add_userchats(to_id);
        // System.out.println(temp.chatlist);
        // Update update = new Update().set("chatlist", temp.chatlist);
        // mongoTemplate.updateFirst(query, update, ChatList.class);

        // // Check if data exists
        // if (chatDataList.isEmpty()) {
        // return ResponseEntity.notFound().build(); // Return 404 if no data found
        // }
        // System.out.println(chatDataList.get(0).chatlist);
        // return ResponseEntity.ok("Updated chatlist");
        // } catch (Exception e) {
        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        // // Handle internal server error
        // }
        String USER_NAME = Singleton.getInstance().getUsername();
        try {

            chatservice.updateChatlist(USER_NAME, to_id);
            // Check if data exists

            return ResponseEntity.ok("Updated chatlist");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

            // Handle internal server error
        }
    }

    @PostMapping("/chat")
    public ResponseEntity<String> sendMessage(@RequestParam("content") MultipartFile file,
            @RequestParam("data_type") String data_type,
            @RequestParam("from_id") String from_id1,
            @RequestParam("to_id") String to_id,
            @RequestParam("time") String time) {
        String from_id = Singleton.getInstance().getUsername();
        System.out.println("CHAT");
        // System.out.println(username);
        try {
            // Parse time string to LocalDateTime
            // LocalDateTime dateTime = LocalDateTime.parse(time,
            // DateTimeFormatter.ISO_DATE_TIME);

            // Create Binary object from file content
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy, hh:mm:ss a");
            Date timestamp = dateFormat.parse(time);
            Binary binaryContent = new Binary(file.getBytes());
            // Date time_t = new Date(time);
            // Create Chat_data object
            Chat_data chatData = new Chat_data(from_id, to_id, timestamp, binaryContent, data_type);

            // Save to MongoDB
            System.out.println(timestamp);
            System.out.println(to_id);
            mongoTemplate.save(chatData);

            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.ok("ERROR");

        }
    }

    @PostMapping(path = "/share/post", consumes = { "multipart/form-data" })
    public ResponseEntity<String> sharePost(
            @RequestParam("userId2") String fromUser1,
            @RequestParam("file") String fileData,
            @RequestParam("contentType") String contentType,
            @RequestParam("value") String toUser) throws IOException {
        String fromUser = Singleton.getInstance().getUsername();

        // Check content type and adjust if necessary
        if ("image".equals(contentType)) {
            contentType = "image/jpeg";
        } else if ("video".equals(contentType)) {
            contentType = "video/mp4";
        }

        // Decode the Base64 encoded file content
        // byte[] decodedFileData = Base64.getDecoder().decode(fileData);

        // Create a Binary object from the decoded binary data

        byte[] bytes = java.util.Base64.getDecoder().decode(fileData);

        // Convert the byte array to a Binary object
        Binary binaryData = new Binary(bytes);

        // org.bson.types.Binary binaryContent = new org.bson.types.Binary(fileData);

        // Create a Chat_data object and save it to MongoDB
        Chat_data chatdata = new Chat_data(fromUser, toUser, new Date(),
                binaryData, contentType);
        mongoTemplate.save(chatdata);

        return ResponseEntity.ok("Shared successfully");
    }

    // @PostMapping(path = "/share/post", consumes = { "multipart/form-data" })
    // public ResponseEntity<String> sharePost(
    // @RequestParam("userId2") String fromUser1,
    // @RequestParam("file") Binary fileData,
    // @RequestParam("contentType") String contentType,
    // @RequestParam("value") String toUser) throws IOException {
    // String fromUser = Singleton.getInstance().getUsername();

    // // Check content type and adjust if necessary
    // if ("image".equals(contentType)) {
    // contentType = "image/jpeg";
    // } else if ("video".equals(contentType)) {
    // contentType = "video/mp4";
    // }
    // Binary binaryContent = fileData;
    // // Decode the Base64 encoded file content
    // // byte[] decodedFileData = Base64.getDecoder().decode(fileData);

    // // // Create a Binary object from the decoded binary data
    // // org.bson.types.Binary binaryContent = new
    // // org.bson.types.Binary(decodedFileData);

    // // Create a Chat_data object and save it to MongoDB
    // Chat_data chatdata = new Chat_data(fromUser, toUser, new Date(),
    // binaryContent, contentType);
    // mongoTemplate.save(chatdata);

    // return ResponseEntity.ok("Shared successfully");
    // }

    @GetMapping(path = "/deletechat/{to_id}/{from_id}/{timestamp}")
    public ResponseEntity<String> sharePost(@PathVariable("to_id") String to_id,
            @PathVariable("from_id") String from_id1, @PathVariable("timestamp") String timestamp)
            throws ParseException {
        // Date date = new Date();
        String from_id = Singleton.getInstance().getUsername();
        // System.out.println("DEL CHAT");
        // System.out.println(username);
        System.out.println(timestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date timestamp_1 = dateFormat.parse(timestamp);
        // Parse the timestamp string into a Date object
        // Date time_t;

        // time_t = dateFormat.parse(timestamp);
        System.out.println("222");
        System.out.println(timestamp_1);

        // Construct the query to remove the chat data
        Query query = new Query(Criteria.where("from_id").is(from_id)
                .and("to_id").is(to_id)
                .and("time").is(timestamp_1));

        // Remove the chat data matching the query
        mongoTemplate.remove(query, Chat_data.class);
        System.out.println("DELETING SUCCESS");

        return ResponseEntity.ok("Deleted successfully");

    }

}
