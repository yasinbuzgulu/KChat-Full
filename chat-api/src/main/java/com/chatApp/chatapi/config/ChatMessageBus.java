package com.chatApp.chatapi.config;

import com.chatApp.chatapi.model.Message;

/**
 * Mesajların emit edildiği (tüketildiği) arayüz
 */
public interface ChatMessageBus {
    void emit(Message message);
}
