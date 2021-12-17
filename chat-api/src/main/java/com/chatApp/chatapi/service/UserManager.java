package com.chatApp.chatapi.service;

import com.chatApp.chatapi.model.User;
import com.chatApp.chatapi.repository.message.MessageRepository;
import com.chatApp.chatapi.repository.user.DefaultMongoUserRepository;
import com.chatApp.chatapi.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

/**
 * Kullanıcı üzerindeki işlemlerin yönetiminine yardımcı olan servis sınıfı
 */
@Service
public class UserManager {

    private final UserRepository userRepository;
    @Autowired
    UserManager userManager;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    DefaultMongoUserRepository defaultMongoUserRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }


    /**
     * Kullanıcı girişinde kullanıcı daha önce yok ise yeni kullanıcı oluşturan metod
     *
     * @param userName: kontrol edilen kullanıcı ismi
     * @return: kullanıcıyı döner
     */
    public User findOrCreateUser(String userName) {
        User existingUser = userRepository.findByName(userName);
        if (existingUser != null) {
            existingUser.setLoginTime(Date.from(Instant.now()).getTime());
            return userRepository.saveObject(existingUser);
        } else {
            return userRepository.save(userName);
        }
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User getUserByConnectionId(String connectionId) {
        return userRepository.findByConnectionId(connectionId);
    }

    public void updateUser(User newUser, String name) {
        User user = userRepository.findByName(name);

        user.setId(newUser.getId());
        user.setName(newUser.getName());
        user.setLoginTime(newUser.getLoginTime());
        user.setExitTime(newUser.getExitTime());
        user.setConnectionId(newUser.getConnectionId());
        userRepository.saveObject(user);
    }


}
