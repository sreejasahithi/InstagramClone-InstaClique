package com.insta.Instagram_clone.Chat.model;

// import com.insta.Instagram_clone.Chat.model.ChatBox;

import java.util.*;

public class ChatList {

    List<ChatBox> chatlist = new ArrayList<>();

    ChatList() {

    }

    private void delete_userchat(String to_id) {
        for (ChatBox friend : chatlist) {
            if (friend.to_id == to_id) {
                chatlist.remove(friend);
                break;
            }
        }
    }

    private void add_userchatS(String to_id) {
        ChatBox new_chat = new ChatBox(to_id);
        chatlist.add(new_chat);
    }

}
