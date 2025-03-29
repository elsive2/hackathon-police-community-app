package com.hackathon.police_community_app.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketSecurity
@Configuration
public class WebSocketSecurityConfig implements WebSocketMessageBrokerConfigurer {
    @Bean
    public AuthorizationManager<Message<?>> messageMatcherAuthorizationManager(
            MessageMatcherDelegatingAuthorizationManager.Builder messages
    ) {
        return messages
//                .simpDestMatchers("/app/**").authenticated()
//                .simpSubscribeDestMatchers("/user/queue/**").authenticated()
                .anyMessage().permitAll()
                .build();
    }
}
