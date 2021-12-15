package com.chatApp.chatapi.kafka;

import com.chatApp.chatapi.model.Message;
import com.chatApplication.avro.message.MessageAvro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Apache kafkanın listener (consumer) kısmının yapıldığı sınıf
 */
@Component
public class KafkaEventHandler {

    private SimpMessagingTemplate template;

    @Autowired
    public KafkaEventHandler(SimpMessagingTemplate template) {
        this.template = template;
    }

    /**
     * Mesajların dinlendiği metod
     *
     * @param messageAvro: dinlenen mesaj
     */
    @KafkaListener(topics = "chat")
    public void listen(MessageAvro messageAvro) {
        Message message = new Message(messageAvro.getId(), messageAvro.getUserName(),
                messageAvro.getUserId(), messageAvro.getText(), new Date(Long.valueOf(messageAvro.getDate())));
        template.convertAndSend("/chat", message);
    }

}
