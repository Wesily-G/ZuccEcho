package com.example.zuccecho.Controller;

import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Entity.Feedback;
import com.example.zuccecho.Repository.AnswerSheetRepository;
import com.example.zuccecho.Repository.FeedbackRepository;
import com.example.zuccecho.Services.FeedBackControllerServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("FeedBack")
public class FeedBackController implements FeedBackControllerServices {
    @Autowired
    private FeedbackRepository fr;
    @Autowired
    private AnswerSheetRepository asr;

    //应该是前端干的事把
//    public boolean publicQuestion(long lectureID,long modelID){
//
//        //...
//
//        return false;
//    }

    @GetMapping("checkSpecificContent/{id}")
    public ResponseData checkSpecificContent(@PathVariable("id") long answersheetID){
        ResponseData rsp = new ResponseData();

        try{
            AnswerSheet t = asr.findById(answersheetID).get();
            rsp.setRspData(t);
        }catch(Exception e){
            if(e instanceof MethodArgumentNotValidException){
                rsp.setError();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }

        return rsp;
    }
    @GetMapping("findFeedback/{id}")
    public ResponseData findFeedback(@PathVariable("id") Long feedbackID){
        ResponseData rsp = new ResponseData();
        try{
            Feedback t = fr.findById(feedbackID).get();
//            Feedback t = fr.findByAnswersheetsID(feedbackID);
            rsp.setRspData(t);
        }catch(Exception e){
            if(e instanceof MethodArgumentNotValidException){
                rsp.setError();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }
        return rsp;
    }

    @PostMapping("fillAnswerSheet")
    public void fillAnswerSheet(){
        AnswerSheet as = new AnswerSheet();
        as.setStudentID(31901032L);
        ArrayList<String> t = new ArrayList<String>();
        t.add("How dare you?");
        as.setAnswers(t);
        asr.save(as);
    }

    @PostMapping("fillFeedback")
    public void fillFeedback(){
        Feedback fb = new Feedback();
        fb.setClassID(1234123L);
        fb.setTeacherID(1235L);
        ArrayList<Long> t = new ArrayList<Long>();
        t.add(1L);
        fb.setAnswersheetsID(t);
        fr.save(fb);
    }

    public HashMap<String,ArrayList<String>> feedbackStatistics(long feedbackID){

        //...(暂时不用实现)

        return null;
    }
}
