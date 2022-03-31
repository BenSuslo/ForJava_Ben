package com.example.online_document_1.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RabbitProducer {
    /**
     * 消息发布者
     */
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendOnlineDocumentQueue(){
        Date date = new Date();
        String dataString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        System.out.println("[OnlineDocumentQueue] send msg:" + dataString);
        this.rabbitTemplate.convertAndSend("onlineDocumentQueue", dataString);
    }
}
