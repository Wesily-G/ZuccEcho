package com.example.zuccecho.QueueManager.Consumer;

import com.example.zuccecho.QueueManager.ZuccEchoMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "#{topicStudentStatQue.name}")
public class TopicStudentConsumer {
    private final Logger logger = LoggerFactory.getLogger(TopicStudentConsumer.class);
    private String name = "TopicStudentConsumer";

    @RabbitHandler
    public void handleMsg(ZuccEchoMessage msg){
        logger.warn("[{}]got [{}]", this.name, msg.stringfy());
        logger.warn("[{}]TopicStudentConsumer do statistics by time.", this.name);
    }
}
