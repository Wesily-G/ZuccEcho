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
@RabbitListener(queues = "#{fanoutNotifyQue.name}")
public class FanoutNotifyConsumer {
    private final Logger logger = LoggerFactory.getLogger(FanoutNotifyConsumer.class);
    private String name = "Fanout-Notify";

    @RabbitHandler
    public void handleMsg(ZuccEchoMessage msg){
        logger.warn("[{}]got [{}]", this.name, msg.stringfy());
        logger.warn("[{}]FanoutTrackConsumer do notify.", this.name);
    }
}
