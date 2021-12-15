package com.chatApp.chatapi.repository.user;

import com.chatApp.chatapi.model.Message;
import com.chatApp.chatapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * KUllanıcı verilerinin kaydolunduğu arayüz
 */
@Repository
public interface UserRepository {
    User findByName(String userName);

    User find(String id);

    User save(String userName);

    User saveObject(User user);

    List<User> findAllUsers();
}
