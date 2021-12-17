package com.chatApp.chatapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Kullanıcı nesnesinin bütün halinin sınıfı -kullanıcı içinde kullanılan ve tutulan veriler bütünü-
 */
public class User implements Serializable {

    /**
     * Kullanıcı id si
     */
    private String id;

    /**
     * Kullanıcı ismi(nickname)
     */
    private String name;

    /**
     * Sisteme en son giriş yapma vakti
     */
    private Long loginTime;

    /**
     * Sistemden en son çıkış yaptığı vakit
     */
    private Long exitTime;

    private String connectionId;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String id, String name, Long loginTime, Long exitTime) {
        this.id = id;
        this.name = name;
        this.loginTime = loginTime;
        this.exitTime = exitTime;
    }

    public User(String id, String name, Long loginTime, Long exitTime, String connectionId) {
        this.id = id;
        this.name = name;
        this.loginTime = loginTime;
        this.exitTime = exitTime;
        this.connectionId = connectionId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExitTime() {
        return exitTime;
    }

    public void setExitTime(Long exitTime) {
        this.exitTime = exitTime;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }
}
