package com.insta.Instagram_clone.Network.model;

public interface Handler {
    void setNextHandler(Handler handler);

    void handleRequest(String user, String myUser);
}
