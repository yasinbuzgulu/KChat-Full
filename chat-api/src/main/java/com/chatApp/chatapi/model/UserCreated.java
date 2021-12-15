package com.chatApp.chatapi.model;

import javax.validation.constraints.NotNull;

/**
 * Gereksinimler çerçevesinde ölçeklenmiş kullanıcı nesnesi sınıfı
 */
public class UserCreated {

    /**
     * Kullanıcı ismi
     */
    @NotNull
    private String name;

    public UserCreated() {
    }

    public UserCreated(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
