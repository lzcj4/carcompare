package com.carcompare.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    @Autowired
    WebSocketHelper webSocketHelper;

    /**
     * 通知用户被踢下线
     * @param token
     */
    public void notifyKickout(String token){
        webSocketHelper.sendToUser(token, "/kickout", "该账号在异地登陆");
    }
}
