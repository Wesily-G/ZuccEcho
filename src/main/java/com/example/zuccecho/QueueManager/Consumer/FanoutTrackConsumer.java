package com.example.zuccecho.QueueManager.Consumer;

import com.example.zuccecho.QueueManager.ZuccEchoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author pengbin
 * @version 1.0
 */
@Component
@RabbitListener(queues = "#{fanoutTrackQue.name}")
public class FanoutTrackConsumer {
    private final Logger logger = LoggerFactory.getLogger(FanoutTrackConsumer.class);
    private String name = "Fanout-Tracker";

    @RabbitHandler
    public void handleMsg(ZuccEchoMessage msg){
        logger.warn("[{}]got [{}]", this.name, msg.stringfy());
        logger.warn("[{}]FanoutTrackConsumer do track status.", this.name);
    }
}
