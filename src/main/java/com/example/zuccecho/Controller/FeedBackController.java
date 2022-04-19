package com.example.zuccecho.Controller;

import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Entity.Feedback;
import com.example.zuccecho.Repository.AnswerSheetRepository;
import com.example.zuccecho.Repository.FeedbackRepository;
import com.example.zuccecho.Services.FeedBackServices;
import com.example.zuccecho.Services.StudentAnswerServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("FeedBack")
public class FeedBackController {
    @Autowired
    private FeedBackServices feedBackServices;

    //应该是前端干的事把
//    public boolean publicQuestion(long lectureID,long modelID){
//
//        //...
//
//        return false;
//    }

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
