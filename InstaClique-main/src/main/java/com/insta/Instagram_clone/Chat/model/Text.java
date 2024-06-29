package com.insta.Instagram_clone.Chat.model;

public class Text implements Content {
    private String content;

    public Text(String content) {
        this.content = content;
    }

    @Override
    public String getType() {
        return "Text";
    }

    // Other methods if needed
}
