package com.insta.Instagram_clone.Chat.model;
// import com.insta.Instagram_clone.Chat.model.Content;

public class Image implements Content {
    String path;

    public Image(String path) {
        this.path = path;
    }

    @Override
    public String getType() {
        return "Video";
    }
}