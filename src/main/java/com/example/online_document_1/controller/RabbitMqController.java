package com.example.online_document_1.controller;

import com.example.online_document_1.service.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMqController {
    @Autowired
    private RabbitProducer rabbitProducer;

    @GetMapping("/sendOnlineDocumentQueue")
    public Object sendOnlineDocumentQueue(){
        rabbitProducer.sendOnlineDocumentQueue();
        return "success";
    }
}
