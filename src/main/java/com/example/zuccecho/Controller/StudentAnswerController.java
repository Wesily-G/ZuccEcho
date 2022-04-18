package com.example.zuccecho.Controller;

import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Entity.Lecture;
import com.example.zuccecho.Repository.AnswerSheetRepository;
import com.example.zuccecho.Repository.FeedbackRepository;
import com.example.zuccecho.Repository.StudentRepository;
import com.example.zuccecho.Services.StudentAnswerControllerServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestController;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentAnswerController implements StudentAnswerControllerServices {
    @Autowired
    private FeedbackRepository fr;
    @Autowired
    private AnswerSheetRepository asr;

    //create new AnswerSheet
    public ResponseData writeAnswerSheet(ArrayList<String> list, Long studentID){
        ResponseData rsp = new ResponseData();

        try{
            long ID=0;
            List<AnswerSheet> answerSheets = asr.findAll();
            for(AnswerSheet a:answerSheets){
                if(a.getId()>ID)
                    ID=a.getId()+1;
            }

            AnswerSheet t = new AnswerSheet();
            t.setId(ID);
            t.setStudentID(studentID);
            t.setAnswers(list);
            asr.save(t);

            rsp.setRspData(new Boolean(Boolean.TRUE));
        }catch (Exception e){
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

    public ResponseData checkHistoricalAnswerList(long studentID,long feedbackID){
        ResponseData rsp = new ResponseData();

        try{
            ArrayList<Long> ansIDs = fr.findById(feedbackID).get().getAnswersheetsID();
            ArrayList<AnswerSheet> answers = new ArrayList<AnswerSheet>();
            for(Long t: ansIDs){
                AnswerSheet t_answer = asr.findById(t).get();
                if(t_answer.getStudentID().equals(studentID))
                    answers.add(t_answer);
            }
            rsp.setRspData(answers);
        }catch (Exception e){
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
}
