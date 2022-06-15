package com.co.indra.coinmarketcap.watchlist.messaging;

import com.co.indra.coinmarketcap.watchlist.models.Response.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlertsProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendNotification(Notification notification) {
        try {
            String message = objectMapper.writeValueAsString(notification);
            rabbitTemplate.convertAndSend("alerts", message);
        } catch (JsonProcessingException exc) {
            exc.printStackTrace();
        }
    }
}
