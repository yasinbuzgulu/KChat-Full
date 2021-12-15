package com.chatApp.chatapi.service;

import com.chatApp.chatapi.config.ChatMessageBus;
import com.chatApp.chatapi.model.Message;
import com.chatApp.chatapi.model.User;
import com.chatApp.chatapi.repository.message.MessageRepository;
import com.chatApp.chatapi.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

/**
 * Mesaj üzerindeki işlemlerin yönetiminine yardımcı olan servis sınıfı
 */
@Service
public class MessageManager {
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private ChatMessageBus chatMessageBus;

    public MessageManager(
            MessageRepository messageRepository,
            UserRepository userRepository,
            ChatMessageBus chatMessageBus
    ) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.chatMessageBus = chatMessageBus;
    }

    /**
     * Tüm mesajları çağıran metod
     *
     * @return: tüm mesajları döner
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAllOrderByDate();
    }

    /**
     * Mesajı kaydeden metod
     *
     * @param userId: Mesaı yazan kullanıcı
     * @param text:   mesaj metni
     * @return: kullanıcı boş değilse kaydı döner/ boş ise hata fırlatır
     */
    public Message save(String userId, String text) {
        User user = userRepository.find(userId);
        if (user != null) {
            return messageRepository.save(new Message(user.getName(), userId, text, Date.from(Instant.now())));
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * Mesajı gönderim sırasıyla consume a gönderen metod
     *
     * @param message
     */
    public void send(Message message) {
        chatMessageBus.emit(message);
    }
}
