package com.chatApp.chatapi.model;

import com.chatApp.chatapi.config.ValueObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Mesaj nesnesinin bütün halinin sınıfı -mesaj içinde kullanılan ve tutulan veriler bütünü-
 */
public class Message extends ValueObject implements Serializable {
    /**
     * Mesaj id si
     */
    private String id;

    /**
     * Kullanıcının kullanıcı ismi (nickname)
     */
    private String userName;

    /**
     * Kullanıcı is si
     */
    private String userId;

    /**
     * Mesaj metni
     */
    private String text;

    /**
     * Mesaj gönderim tarihi
     */
    private Date date;

    public Message() {
    }

    public Message(String id, String userName, String userId, String text, Date date) {
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.text = text;
        this.date = date;
    }

    public Message(String userName, String userId, String text, Date date) {
        this(null, userName, userId, text, date);
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }
}
