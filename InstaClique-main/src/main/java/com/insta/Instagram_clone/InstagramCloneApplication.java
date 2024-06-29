// // package com.insta.Instagram_clone;

// // import org.springframework.boot.SpringApplication;
// // import org.springframework.boot.autoconfigure.SpringBootApplication;

// // import com.insta.Instagram_clone.Chat.model.*;
// // import com.insta.Instagram_clone.Chat.service.*;
// // import com.insta.Instagram_clone.Chat.repository.*;
// // import java.util.*;
// // import org.springframework.context.ApplicationContext;

// // //Jackson to parse the mongodb data
// // import com.fasterxml.jackson.databind.ObjectMapper;

// // @SpringBootApplication
// // public class InstagramCloneApplication {

// // 	// public static void main(String[] args) {
// // 	// SpringApplication.run(InstagramCloneApplication.class, args);

// // 	// // testing
// // 	// ChatRepository repo;
// // 	// ChatService cs = new ChatService();
// // 	// List<Chat_each> l = cs.getAllChats();

// // 	// for (Chat_each x : l) {
// // 	// System.out.println(x.get_to_id());
// // 	// }

// // 	// }

// // 	public static void main(String[] args) {
// // 		ApplicationContext context = SpringApplication.run(InstagramCloneApplication.class, args);

// // 		// Retrieve the ChatService bean from the application context
// // 		// ChatService chatService = context.getBean(ChatService.class);

// // 		// // Use the ChatService
// // 		// // chatService.getAllChats();
// // 		// List<Chat_each> chats = chatService.getAllChats();

// // 		// // Convert the list of Chat_each objects to JSON
// // 		// ObjectMapper objectMapper = new ObjectMapper();
// // 		// String jsonChats = objectMapper.writeValueAsString(chats);

// // 		// System.out.println(jsonChats);
// // 		// // List<Chat_each> chats = chatRepository.findAll();
// // 		// // System.out.println("All chats:");
// // 		// // for (Chat_each chat : chats) {
// // 		// // System.out.println(chat.toString());
// // 		// // }

// // 		ChatService chatService = context.getBean(ChatService.class);

// // 		// Use the ChatService to get all chats
// // 		List<Chat_each> chats = chatService.getAllChats();

// // 		// Print or display the chats
// // 		for (Chat_each chat : chats) {
// // 			System.out.println(chat.get_to_id());
// // 		}
// // 	}

// // }

// package com.insta.Instagram_clone;

// import com.insta.Instagram_clone.Chat.model.Chat_each;
// import com.insta.Instagram_clone.Chat.repository.ChatRepository;
// import com.insta.Instagram_clone.Chat.service.ChatService;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.Bean;

// import java.util.List;

// @SpringBootApplication
// public class InstagramCloneApplication {

// 	public static void main(String[] args) {
// 		ApplicationContext context = SpringApplication.run(InstagramCloneApplication.class, args);
// 		// @Bean
// 		// CommandLineRunner runner(ChatRepository cr){
// 		// return args->{
// 		// Chat_each c= new Chat_each("3","4","String");
// 		// }
// 		// };
// 		// Retrieve the ChatService bean from the application context
// 		// ChatService chatService = context.getBean(ChatService.class);

// 		// // Use the ChatService to get all chats
// 		// List<Chat_each> chats = chatService.getAllChats();

// 		// // Display the chats
// 		// for (Chat_each chat : chats) {
// 		// System.out.println("To ID: " + chat.get_to_id());
// 		// System.out.println("From ID: " + chat.get_from_id());
// 		// // System.out.println("Content Type: " + chat.get_Content().getType());
// 		// }
// 	}
// }
package com.insta.Instagram_clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class InstagramCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstagramCloneApplication.class, args);

		// Retrieve the ChatService bean from the application context
		// ChatService chatService = context.getBean(ChatService.class);

		// // Use the ChatService to get all chats
		// List<Chat_each> chats = chatService.getAllChats();

		// // Display the chats
		// for (Chat_each chat : chats) {
		// System.out.println("To ID: " + chat.get_to_id());
		// System.out.println("From ID: " + chat.get_from_id());
		// // System.out.println("Content Type: " + chat.get_Content().getType());
		// }
	}

	// @Bean
	// public CommandLineRunner runner(ChatRepository cr) {
	// return args -> {
	// Chat_each c = new Chat_each("3", "4", "String");
	// // Perform any operation using ChatRepository if needed
	// };
	// }
}
