package com.example.zuccecho.Controller;

import com.example.zuccecho.QueueManager.Constants;
import com.example.zuccecho.QueueManager.ZuccEchoMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
@ResponseBody
public class TestController {
    @Autowired
    private AmqpTemplate mqService;
    @Autowired
    @Qualifier("direct")
    private DirectExchange directExchange;

    @PostMapping(value="errorPublic")
    public void issueError(){
        ZuccEchoMessage msg = new ZuccEchoMessage(ZuccEchoMessage.CATEGORY_ERROR);
        msg.appendContent("error",null);
        mqService.convertAndSend(directExchange.getName(),Constants.KEY_WARN,msg);
        mqService.convertAndSend(directExchange.getName(),Constants.KEY_FATAL,msg);
    }

}
