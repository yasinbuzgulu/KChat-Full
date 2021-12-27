package com.chatApp.chatapi.controller;

import com.chatApp.chatapi.model.Message;
import com.chatApp.chatapi.model.MessageCreated;
import com.chatApp.chatapi.service.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Mesajlara ait işlemlerin yönetimini yapan sınıf
 */
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://localhost:8080",
        "http://10.33.23.88:4200", "http://10.33.23.88:8080", "http://10.33.23.88:3000",
        "http://192.168.1.105:3000", "http://192.168.1.105:4200", "http://192.168.1.105:8080"}, maxAge = 3600)
@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageManager messageManager;

    @Autowired
    public MessageController(
            MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    /**
     * Tüm mesajları çağıran metod
     *
     * @return: tüm mesajları
     */
    @GetMapping()
    public List<Message> getMessages() {
        return messageManager.getAllMessages();
    }

    /**
     * Yeni mesaj oluşturan kaydeden metod
     *
     * @param messageCreated: kullanıcıdan gelen mesaj nesnesi
     */
    @PostMapping("/new")
    public void addNewMessage(@Valid @RequestBody MessageCreated messageCreated) {
        try {
            Message message = messageManager.save(messageCreated.getUserId(), messageCreated.getText());
            messageManager.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
