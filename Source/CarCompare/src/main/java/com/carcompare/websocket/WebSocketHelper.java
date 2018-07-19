package com.carcompare.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketHelper {
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    public void sendToUser(String user, String destination, Object payload) {
        messagingTemplate.convertAndSendToUser(user, destination, payload);
    }
}
