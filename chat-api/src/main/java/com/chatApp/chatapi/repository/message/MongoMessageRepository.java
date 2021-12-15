package com.chatApp.chatapi.repository.message;

import com.chatApp.chatapi.model.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * MongoDb kullanımını mesajlar için yöneten sınıf
 */
@Repository
public class MongoMessageRepository implements MessageRepository {

    private DefaultMongoMessageRepository defaultMongoMessageRepository;
    private MongoOperations mongoOperations;

    public MongoMessageRepository(
            DefaultMongoMessageRepository defaultMongoMessageRepository,
            MongoOperations mongoOperations
    ) {
        this.defaultMongoMessageRepository = defaultMongoMessageRepository;
        this.mongoOperations = mongoOperations;
    }

    /**
     * Mesajların kayıt olunduğu metod
     *
     * @param message: kaydedilen mesaj
     * @return: mesaj kaydını döner
     */
    @Override
    public Message save(Message message) {
        return defaultMongoMessageRepository.save(message);
    }

    /**
     * Mesajları tarihe göre listeleyen -sıralayan- metod
     *
     * @return: Mesajları döner
     */
    @Override
    public List<Message> findAllOrderByDate() {
        Query query = new Query();
        query.with(new Sort(ASC, "date"));
        return mongoOperations.find(query, Message.class);
    }
}
