package com.insta.Instagram_clone.Chat.model;

public class ChatFactory {
    public static Chat_each createChat(String to_id, String from_id, Object content) {
        if (content instanceof String) {
            return new Chat_each(to_id, from_id, new Text((String) content));
        } else if (content instanceof Image) {
            return new Chat_each(to_id, from_id, (Image) content);
        } else if (content instanceof Audio) {
            return new Chat_each(to_id, from_id, (Audio) content);
        }
        throw new IllegalArgumentException("Unsupported content type");
    }
}