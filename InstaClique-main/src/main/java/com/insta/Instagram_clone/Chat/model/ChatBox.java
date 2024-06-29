package com.insta.Instagram_clone.Chat.model;

import java.util.*;

// import com.insta.Instagram_clone.Chat.ChatFactory;
public class ChatBox {
    List<Chat_each> chats = new ArrayList<>();
    String to_id;

    ChatBox(String to_id) {
        this.to_id = to_id;
    }

}
