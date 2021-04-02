package com.aiot.web.util;

import com.aiot.web.WebApplication;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Frank
 * @Date: 2021/2/18
 */
@Component
public class Sender {

    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${spring.rabbitmq.binding}")
    private String bindingName;

    @Resource
    private AmqpTemplate amqpTemplate;

    public void send(String message){
        amqpTemplate.convertAndSend(exchangeName,bindingName,message);
    }
}
