package com.example.online_document_1.config;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    /**
     * online document queue
     */
    @Bean
    public Queue onlineDocumentString(){
        return new Queue("onlineDocumentQueue");
    }
}
