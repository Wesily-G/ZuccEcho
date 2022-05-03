package com.example.zuccecho.Controller;

import com.example.zuccecho.DTO.FeedbackDTO;
import com.example.zuccecho.QueueManager.Constants;
import com.example.zuccecho.QueueManager.ZuccEchoMessage;
import com.example.zuccecho.Services.FeedBackServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;

@RestController
@RequestMapping("FeedBack")
@ResponseBody
public class FeedBackController {
    @Autowired
    private FeedBackServices feedBackServices;
    @Autowired
    private AmqpTemplate mqService;
    @Autowired
    private FanoutExchange fanoutExchange;

    @GetMapping("checkSpecificContent/{id}")
    public ResponseData checkSpecificContent(@PathVariable("id") long answersheetID){
        ResponseData rspData = new ResponseData();
        try{
            rspData.setRspData(feedBackServices.checkSpecificContent(answersheetID));
        }catch(Exception e){
            rspData.setFailed();
        }
        return rspData;
    }

    @GetMapping("findFeedback/{id}")
    public ResponseData findFeedback(@PathVariable("id") long feedbackID){
        ResponseData rspData = new ResponseData();
        try{
            rspData.setRspData(feedBackServices.findFeedback(feedbackID));
        }catch(Exception e){
            rspData.setFailed();
        }
        return rspData;
    }

    @PostMapping(value="publicFeedback",produces = "application/json;charset=UTF-8")
    public ResponseData publicFeedback(@RequestBody FeedbackDTO feedbackDTO){
        ResponseData rsp = new ResponseData();
        try{
            feedBackServices.publicFeedback(feedbackDTO);
            rsp.setRspData(feedbackDTO);
        }catch (Exception e){
            if(e instanceof BindException){
                rsp.setError();
                rsp.setRspData("BindException");
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }
        ZuccEchoMessage msg = new ZuccEchoMessage(ZuccEchoMessage.CATEGORY_PAPER);
        msg.appendContent("rspData",rsp);
        mqService.convertAndSend(fanoutExchange.getName(),"",msg);
        return rsp;
    }

    @PostMapping("fillAnswerSheet")
    public void fillAnswerSheet(){
        feedBackServices.fillAnswerSheet();
    }

    @PostMapping("fillFeedback")
    public void fillFeedback(){
        feedBackServices.fillFeedback();
    }

    @GetMapping("feedbackStatistics/{id}")
    public ResponseData feedbackStatistics(@PathVariable("id") long feedbackID){
        ResponseData rspData = new ResponseData();
        try{
            rspData.setRspData(feedBackServices.feedbackStatistics(feedbackID));
        }catch(Exception e){
            rspData.setFailed();
        }
        return rspData;
    }

    @PostMapping("reminderForNotFilled/{id}")
    public ResponseData ReminderForNotFilled(long feedbackID){
        ResponseData rspData = new ResponseData();
        try {
            feedBackServices.ReminderForNotFilled(feedbackID);
        }catch (Exception e){
            rspData.setRspData(new Boolean(Boolean.FALSE));
        }

        return rspData;
    }
}
