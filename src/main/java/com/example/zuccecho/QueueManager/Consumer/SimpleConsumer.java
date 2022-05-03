package com.example.zuccecho.QueueManager.Consumer;

import com.example.zuccecho.QueueManager.Constants;
import com.example.zuccecho.QueueManager.ZuccEchoMessage;
import com.example.zuccecho.Support.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {Constants.QUE_SIMPLE})
public class SimpleConsumer {
    private final Logger logger = LoggerFactory.getLogger(SimpleConsumer.class);
    private String name = "SimpleConsumer";

    @RabbitHandler
    public void handleMsg(ZuccEchoMessage msg){
        logger.warn("[{}]got [{}]", this.name, msg.stringfy());
        logger.warn("[{}]SimpleConsumer handle the message .", this.name);
    }

    @RabbitHandler
    public void handleRspMsg(ResponseData msg){
        logger.warn("[{}]got [{}]", this.name, msg.getRspData().toString());
        logger.warn("[{}]SimpleConsumer handle the message .", this.name);
    }
}
