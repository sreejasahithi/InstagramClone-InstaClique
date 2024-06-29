// package com.insta.Instagram_clone.Chat.service;

// import com.insta.Instagram_clone.Chat.model.Chat_each;
// import com.insta.Instagram_clone.Chat.repository.ChatRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// // import org.springframework.beans.factory.annotation.Autowired;

// import java.util.List;

// @Service
// public class ChatService {
//     // @Autowired
//     // private ChatRepository chatRepository;
//     private final ChatRepository chatRepository;

//     @Autowired
//     public ChatService(ChatRepository chatRepository) {
//         this.chatRepository = chatRepository;
//     }

//     public List<Chat_each> getAllChats() {
//         // List<Chat_each> chats = chatRepository.findAll();
//         // System.out.println("All chats:");
//         // for (Chat_each chat : chats) {
//         // System.out.println(chat.toString());
//         // }
//         return chatRepository.findAll();
//     }

//     // Other service methods for CRUD operations
// }
package com.insta.Instagram_clone.Chat.service;

import com.insta.Instagram_clone.Chat.model.Chat_each;
import com.insta.Instagram_clone.Chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<Chat_each> getAllChats() {
        return chatRepository.findAll();
    }
}
