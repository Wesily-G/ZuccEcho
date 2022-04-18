package com.example.zuccecho.Controller;

import com.example.zuccecho.DTO.AnswerSheetDTO;
import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Repository.AnswerSheetRepository;
import com.example.zuccecho.Repository.FeedbackRepository;
import com.example.zuccecho.Services.AnalysisServices;
import com.example.zuccecho.Services.FeedBackServices;
import com.example.zuccecho.Services.QAModelServices;
import com.example.zuccecho.Services.StudentAnswerServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("studentAnswer")
public class StudentAnswerController {
    @Autowired
    private StudentAnswerServices answerServices;

    //create new AnswerSheet
    public ResponseData writeAnswerSheet(AnswerSheetDTO answerSheetDTO){
        ResponseData rsp = new ResponseData();
        try{
            answerServices.writeAnswerSheet(answerSheetDTO);
            rsp.setRspData(answerSheetDTO);
        }catch (Exception e){
            if(e instanceof BindException){
                rsp.setError();
                rsp.setRspData("BindException");
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }
        return rsp;
    }

    public ResponseData checkHistoricalAnswerList(long studentID,long feedbackID){
        ResponseData rsp = new ResponseData();

        try{
            ArrayList<AnswerSheet> answers = new ArrayList<AnswerSheet>();
            answers = answerServices.checkHistoricalAnswerList(studentID,feedbackID);
            rsp.setRspData(answers);
        }catch (Exception e){
            if(e instanceof MethodArgumentNotValidException){
                rsp.setError();
                rsp.setRspData("MethodArgumentNotValidException");
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }

        return rsp;
    }
}
