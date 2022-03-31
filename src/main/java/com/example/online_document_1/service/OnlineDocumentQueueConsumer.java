package com.example.online_document_1.service;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "onlineDocumentQueue")
public class OnlineDocumentQueueConsumer {
    /**
     * 消息消费者
     * 接收消息并处理
     */
    @RabbitHandler
    public void received(String msg){
        System.out.println("[onlineDocumentQueue] received message: " + msg);
    }
}
