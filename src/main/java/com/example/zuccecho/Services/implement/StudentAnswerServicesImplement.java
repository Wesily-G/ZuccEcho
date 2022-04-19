package com.example.zuccecho.Services.implement;

import com.example.zuccecho.DTO.AnswerSheetDTO;
import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Entity.QAModel;
import com.example.zuccecho.Repository.AnswerSheetRepository;
import com.example.zuccecho.Repository.FeedbackRepository;
import com.example.zuccecho.Services.StudentAnswerServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentAnswerServicesImplement implements StudentAnswerServices {
    @Autowired
    private FeedbackRepository fr;
    @Autowired
    private AnswerSheetRepository asr;

    //create new AnswerSheet
    @Cacheable(key = "#p0.getId()",value = "ID#2")
    public AnswerSheet writeAnswerSheet(AnswerSheetDTO answerSheetDTO){
        AnswerSheet answerSheet = new AnswerSheet();
        //错误校验后用entity类存入数据库
        BeanUtils.copyProperties(answerSheetDTO, answerSheet);
        asr.save(answerSheet);
        return answerSheet;
    }

    public ArrayList<AnswerSheet> checkHistoricalAnswerList(long studentID,long feedbackID){
        ArrayList<Long> ansIDs = fr.findById(feedbackID).get().getAnswersheetsID();
        ArrayList<AnswerSheet> answers = new ArrayList<AnswerSheet>();
        for(Long t: ansIDs){
            AnswerSheet t_answer = asr.findById(t).get();
            if(t_answer.getStudentID()==studentID)
                answers.add(t_answer);
        }
        return answers;
    }
}
