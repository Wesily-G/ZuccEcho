package com.example.zuccecho.Services.implement;

import com.example.zuccecho.DTO.FeedbackDTO;
import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Entity.Feedback;
import com.example.zuccecho.Entity.Student;
import com.example.zuccecho.Repository.AnswerSheetRepository;
import com.example.zuccecho.Repository.FeedbackRepository;
import com.example.zuccecho.Repository.StudentRepository;
import com.example.zuccecho.Services.FeedBackServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class FeedBackServicesImplement implements FeedBackServices {
    @Autowired
    private FeedbackRepository fr;
    @Autowired
    private AnswerSheetRepository asr;
    @Autowired
    private StudentRepository sr;

    //前端获得模板修改后自行发布问卷，后端会将修改过后的问卷以model形式存储在feedback中
    //!!!!!!!!!不知道对不对需要测试!!!!!!!!!!!!
    public Feedback publicFeedback(FeedbackDTO feedbackDTO){
        Feedback t = null;
        try{
            //发布问卷
            BeanUtils.copyProperties(feedbackDTO,t);
            fr.save(t);
            //将问卷发布到所有人，也就是创建到所有人的answersheet,首先找到所有参加课程的学生然后for发布
            ArrayList<Long> studentIds = sr.findStudentsByLectureIdsIsContaining(t.getClassID());
            for (Long stuId : studentIds) {
                AnswerSheet answerSheet = new AnswerSheet();
                answerSheet.setStudentID(stuId);
                answerSheet.setFeedbackID(t.getId());
                asr.save(answerSheet);
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
            return t;
        }
    }

    public AnswerSheet checkSpecificContent(long answersheetID){
        AnswerSheet t = null;
        try{
            t = asr.findById(answersheetID).get();
        }catch(Exception e){
            System.out.println(e);
        }finally {
            return t;
        }
    }

    public Feedback findFeedback(Long feedbackID){
        Feedback t = null;
        try{
            t = fr.findById(feedbackID).get();
        }catch(Exception e){
            System.out.println(e);
        }finally {
            return t;
        }
    }

    public void fillAnswerSheet(){
        AnswerSheet as = new AnswerSheet();
        as.setStudentID(31901032L);
        ArrayList<String> t = new ArrayList<String>();
        t.add("How dare you?");
        as.setAnswers(t);
        asr.save(as);
    }

    public void fillFeedback(){
        Feedback fb = new Feedback();
        fb.setClassID(1234123L);
        fb.setTeacherID(1235L);
        ArrayList<Long> t = new ArrayList<Long>();
        t.add(1L);
        fb.setAnswersheetsID(t);
        fr.save(fb);
    }

    //追踪 应填写人，已填写人，未填写人
    public HashMap<String,ArrayList<String>> feedbackStatistics(long feedbackID){

        //...(暂时不用实现)

        return null;
    }
}
