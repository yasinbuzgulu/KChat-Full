package com.chatApp.chatapi.controller;

import com.chatApp.chatapi.model.User;
import com.chatApp.chatapi.model.UserCreated;
import com.chatApp.chatapi.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.validation.Valid;
import java.sql.Date;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Kullanıcılara ait işlemlerin yönetimini yapan sınıf
 */
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000", "http://localhost:8080" ,
        "http://10.33.23.88:4200", "http://10.33.23.88:8080", "http://10.33.23.88:3000"}, maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserManager userManager;
    private String name;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userManager.getAllUsers();
    }

    /**
     * Kullanıcı adı girildiğinde kontrolü yapılıp yenii kullanıcı oluşturup oluşturmamanın yönetildiği metod
     *
     * @param userCreated: girilen kullanıcı adı(nesnesi)
     * @return: kullanıcı bulunur/kaydedilir
     */
    @PostMapping("/new")
    public User createUser(@Valid @RequestBody UserCreated userCreated) {
        try {
            name = userCreated.getName();
            return this.userManager.findOrCreateUser(userCreated.getName());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An error has occurred, sorry.");
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("name") String name) {
        userManager.updateUser(user, name);
        return new ResponseEntity<>(userManager.getUserByName(name), HttpStatus.OK);
    }

    Set<String> mySet = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());

    @EventListener
    public void onSessionConnectedEvent(SessionConnectedEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        mySet.add(sha.getSessionId());  // System.out.println(sha.getSessionId());
        System.out.println(mySet.size());
        System.out.println("Bir kullanıcı giriş yaptı");
        System.out.println("Giriş yapma zamanı : "+event.getTimestamp());
        setLoginTime(event.getTimestamp());
    }

    @EventListener
    public void onSessionDisconnectEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        mySet.remove(sha.getSessionId());  // System.out.println(sha.getSessionId());
        System.out.println(mySet.size());
        System.out.println("Bir kullanıcı çıkış yaptı");
        System.out.println("Çıkış yapma zamanı : "+event.getTimestamp());
        setExitTime(event.getTimestamp());
//        Date.from(Instant.now()).getTime()
    }
    private void setLoginTime(Long loginTime) {
        User temporaryUser = new User();
        temporaryUser = userManager.getUserByName(name);
        temporaryUser.setLoginTime(loginTime);
        userManager.updateUser(temporaryUser, name);
    }

    private void setExitTime(Long exitTime) {
        User temporaryUser = new User();
        temporaryUser =userManager.getUserByName(name);
        temporaryUser.setExitTime(exitTime);
        userManager.updateUser(temporaryUser, name);
    }
}
