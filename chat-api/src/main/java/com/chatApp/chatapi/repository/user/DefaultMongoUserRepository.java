package com.chatApp.chatapi.repository.user;

import com.chatApp.chatapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Kullanıcı verilerinin aynı zamanda MongoDb database ine kaydeden arayüz
 */
@Repository
public interface DefaultMongoUserRepository extends MongoRepository<User, String> {
    User findByName(String name);

    Optional<User> findById(String id);

    User findUserByConnectionId(String connectionId);
}
