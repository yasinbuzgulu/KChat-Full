package com.chatApp.chatapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Websocket
 * Kullanıcının adını taşıyan bir mesajı kabul eden sunucuyu ve yanıt olarak, sunucu, istemcinin abone olduğu
 * bir kuyruğa bir "selamlama" gönderen metod
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Her birini belirli bir URL'ye eşleyen ve (isteğe bağlı olarak) SockJS yedek seçeneklerini etkinleştiren ve
     * yapılandıran STOMP uç noktalarını kaydeden metod
     *
     * @param registry:
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("http://localhost:4200", "http://localhost:3000", "http://localhost:8080",
                        "http://10.33.23.88:4200", "http://10.33.23.88:8080", "http://10.33.23.88:3000",
                        "http://192.168.1.105:3000", "http://192.168.1.105:4200", "http://192.168.1.105:8080");
    }

    /**
     * WebSocket istemcilerinden alınan ve WebSocket istemcilerine gönderilen iletilerin işlenmesiyle ilgili seçenekleri yapılandıran metod
     *
     * @param registry:
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/chat");
    }


}
