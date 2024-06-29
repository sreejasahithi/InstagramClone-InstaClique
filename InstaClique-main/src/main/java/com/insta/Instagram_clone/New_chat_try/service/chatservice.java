package com.insta.Instagram_clone.New_chat_try.service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.New_chat_try.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.core.MongoTemplate;
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

@Service
public class chatservice {
    @Autowired
    private Chatrepository chatRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    // Singleton.getInstance().getUsername()
    String USER_NAME = Singleton.getInstance().getUsername();

    public List<Chat_data> getChatsorted(String to_id, String from_id1) {

        String from_id = Singleton.getInstance().getUsername();
        // Fetch chat data sorted by timestamp in ascending order
        // Query for documents where from_id is from_id and to_id is to_id
        Query query1 = new Query(Criteria.where("from_id").is(from_id).and("to_id").is(to_id));

        // Query for documents where from_id is to_id and to_id is from_id
        Query query2 = new Query(Criteria.where("from_id").is(to_id).and("to_id").is(from_id));

        // Execute the first query
        List<Chat_data> chatDataList1 = mongoTemplate.find(
                query1.with(Sort.by(Sort.Direction.ASC, "time")),
                Chat_data.class);

        // Execute the second query
        List<Chat_data> chatDataList2 = mongoTemplate.find(
                query2.with(Sort.by(Sort.Direction.ASC, "time")),
                Chat_data.class);

        // Concatenate the resulting lists
        List<Chat_data> ChatDataList = new ArrayList<>(chatDataList1);
        ChatDataList.addAll(chatDataList2);

        // Sort the combined list based on ascending order of time
        Collections.sort(ChatDataList, Comparator.comparing(Chat_data::getTime));

        return ChatDataList;

    }

    public List<String> getChatlist(String from_id) {
        String USER_NAME = Singleton.getInstance().getUsername();

        // Fetch chat data sorted by timestamp in ascending order
        System.out.println("IN FUNCTION");
        System.out.println(USER_NAME);
        Query query = new Query(Criteria.where("userID").is(USER_NAME));
        List<ChatList> chatDataList = mongoTemplate.find(query, ChatList.class);

        System.out.println("CHATLIST");
        // System.out.println(USER_NAME);
        return chatDataList.get(0).chatlist;

    }

    public void updateChatlist(String from_id1, String to_id) {
        String from_id = Singleton.getInstance().getUsername();
        // Fetch chat data sorted by timestamp in ascending order
        Query query = new Query(Criteria.where("userID").is(from_id));
        System.out.println("UPDATE: " + from_id + " " + to_id);
        List<ChatList> chatDataList = mongoTemplate.find(query, ChatList.class);
        ChatList temp = new ChatList(from_id, chatDataList.get(0).chatlist);
        temp.add_userchats(to_id);
        System.out.println(temp.chatlist);
        Update update = new Update().set("chatlist", temp.chatlist);
        mongoTemplate.updateFirst(query, update, ChatList.class);

    }

}