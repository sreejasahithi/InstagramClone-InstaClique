package com.insta.Instagram_clone.Chat.model;

public class Audio implements Content {
    String path;

    public Audio(String path) {
        this.path = path;
    }

    @Override
    public String getType() {
        return "Audio";
    }
}