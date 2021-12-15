package com.chatApp.chatapi.model;

import javax.validation.constraints.NotNull;

/**
 * Gereksinimler çerçevesinde ölçeklenmiş mesaj nesnesi sınıfı
 */
public class MessageCreated {

    /**
     * Kullanıcının yazdığı mesaj metni
     */
    @NotNull
    private String text;

    /**
     * Kullanıcının kim olduğunu tuttuğumuz id (isimlere bağlı tutulmakta)
     */
    @NotNull
    private String userId;

    /**
     * Mesaj oluşturma metodu
     *
     * @param text:   mesaj metni
     * @param userId: mesaj metnini yazan kullanıcı
     */
    public MessageCreated(String text, String userId) {
        this.text = text;
        this.userId = userId;
    }

    /**
     * Mesaj metni çağırma-alma- metodu
     *
     * @return: mesaj metninin döner
     */
    public String getText() {
        return text;
    }

    /**
     * Mesaj metnini yazan kullanıcıyı çağırma-alma- metodu
     *
     * @return: kullanıcıyı döner
     */
    public String getUserId() {
        return userId;
    }
}
