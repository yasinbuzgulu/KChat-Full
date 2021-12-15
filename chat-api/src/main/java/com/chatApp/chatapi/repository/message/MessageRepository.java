package com.chatApp.chatapi.repository.message;

import com.chatApp.chatapi.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mesaj verilerinin kaydolunduğu arayüz
 */
@Repository
public interface MessageRepository {
    Message save(Message message);

    List<Message> findAllOrderByDate();
}
