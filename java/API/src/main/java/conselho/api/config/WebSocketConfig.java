package conselho.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topico");  // Prefixo para a comunicação broadcast
        config.setApplicationDestinationPrefixes("/app");  // Prefixo para a comunicação entre cliente-servidor
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")  // endpoint do WebSocket
                .setAllowedOrigins("http://localhost:3000")  // Restringe quais domínios frontend têm permissão para se conectar (Atualmente o localhost)
                .withSockJS();  // Ativa o support fallback
    }
}