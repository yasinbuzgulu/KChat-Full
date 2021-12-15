package com.chatApp.chatapi.kafka;

import com.chatApp.chatapi.config.ChatMessageBus;
import com.chatApp.chatapi.model.Message;
import com.chatApplication.avro.message.MessageAvro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Apache kafkanın produce edildiği kısmının yapıldığı sınıf
 */
@Component
public class KafkaEmission implements ChatMessageBus {

    private final KafkaTemplate<String, MessageAvro> kafkaTemplate;

    @Autowired
    public KafkaEmission(KafkaTemplate<String, MessageAvro> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Mesajların üretildiği metod
     *
     * @param message: mesaj nesnesi
     */
    public void emit(Message message) {
        MessageAvro messageAvro = new MessageAvro(message.getId(), message.getText(), message.getUserId(),
                message.getUserName(), message.getDate().getTime());
        kafkaTemplate.send("chat", messageAvro);
    }
}
