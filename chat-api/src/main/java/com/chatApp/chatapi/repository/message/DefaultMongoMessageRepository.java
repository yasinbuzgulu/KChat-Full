package com.chatApp.chatapi.repository.message;

import com.chatApp.chatapi.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Mesaj verilerinin mongoDB database ine kaydolunduğu arayüz
 */
@Repository
public interface DefaultMongoMessageRepository extends MongoRepository<Message, String> {
}
