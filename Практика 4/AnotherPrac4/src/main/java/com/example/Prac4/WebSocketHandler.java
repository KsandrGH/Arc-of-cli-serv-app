package com.example.Prac4;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandler extends TextWebSocketHandler {
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String messageContent = message.getPayload();
        // Отправляем обратно тот же текст
        session.sendMessage(new TextMessage(messageContent));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Здесь можно выполнить дополнительные действия при закрытии соединения
    }
}

