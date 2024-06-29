package com.insta.Instagram_clone.New_chat_try.model;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ChatList")
public class ChatList {
    @Id
    private String id;
    public String userID;
    public List<String> chatlist;

    public ChatList(String userID, List<String> chatlist) {
        this.userID = userID;
        this.chatlist = chatlist;

    }

    public void delete_userchat(String to_id) {
        for (String friend : chatlist) {
            if (friend.equals(to_id)) {
                chatlist.remove(friend);
                break;
            }
        }
    }

    public void add_userchats(String to_id) {
        int flag = 0;
        for (String chat_users : chatlist) {
            if (chat_users.equals(to_id)) {
                flag = 1;
                break;

            }
        }
        if (flag == 1)
            return;
        else {
            chatlist.add(to_id);
        }
    }

}
