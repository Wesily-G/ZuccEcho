package com.example.zuccecho.Services.implement;

import com.example.zuccecho.DTO.FeedbackDTO;
import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Entity.Feedback;
import com.example.zuccecho.Entity.Student;
import com.example.zuccecho.Quartz.ReminderForNotFilledSchedule;
import com.example.zuccecho.Repository.AnswerSheetRepository;
import com.example.zuccecho.Repository.FeedbackRepository;
import com.example.zuccecho.Repository.StudentRepository;
import com.example.zuccecho.Services.FeedBackServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Service
public class FeedBackServicesImplement implements FeedBackServices {
    @Autowired
    private FeedbackRepository fr;
    @Autowired
    private AnswerSheetRepository asr;
    @Autowired
    private StudentRepository sr;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    //前端获得模板修改后自行发布问卷，后端会将修改过后的问卷以model形式存储在feedback中
    //!!!!!!!!!不知道对不对需要测试!!!!!!!!!!!!
    @Cacheable(key = "#p0.getId()",value = "FeedBackID#2")  //value指定的是缓存的名字
    public Feedback publicFeedback(FeedbackDTO feedbackDTO){
        Feedback t = null;
        try{
            //发布问卷
            BeanUtils.copyProperties(feedbackDTO,t);
            fr.save(t);
            //将问卷发布到所有人，也就是创建到所有人的answersheet,首先找到所有参加课程的学生然后for发布
            ArrayList<Student> students = sr.findStudentsByLectureIdsIsContaining(t.getClassID());
            for (Student stu : students) {
                AnswerSheet answerSheet = new AnswerSheet();
                answerSheet.setStudentID(stu.getId());
                answerSheet.setFeedbackID(t.getId());
                asr.save(answerSheet);
            }
        }catch(Exception e){
            System.out.println("Exception");
        }
        return t;

    }

    public void ReminderForNotFilled(long feedbackID) {
        //具体事务因为涉及到quartz所以将代码分装到Quartz文件夹中了
        ReminderForNotFilledSchedule.schedule(feedbackID);
    }

    @Cacheable(key = "#p0",value = "FeedBackID#2")
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

    @Cacheable(key = "#p0",value = "FeedBackID#2")
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
    //!!!!!!!!!不知道对不对需要测试!!!!!!!!!!!!
    @Cacheable(key ="#p0",value = "FeedbackStatistic#2")
    public HashMap<String,ArrayList<Long>> feedbackStatistics(long feedbackID){
        HashMap<String,ArrayList<Long>> result = new HashMap<String,ArrayList<Long>>();
        //先通过feedbackid找到应填写人
        ArrayList<AnswerSheet> allAnswersheet = asr.findAnswerSheetByFeedbackID(feedbackID);
        ArrayList<Long> todo = new ArrayList<Long>();
        for (AnswerSheet as:allAnswersheet) {
            todo.add(as.getStudentID());
        }
        result.put("todo",todo);
        //同样的方法获得没写的人
        ArrayList<AnswerSheet> emptyAnswersheet = asr.findAllByAnswersIsNull(feedbackID);
        ArrayList<Long> empty = new ArrayList<Long>();
        for (AnswerSheet as:emptyAnswersheet) {
            empty.add(as.getStudentID());
        }
        result.put("empty",empty);
        //最后获得差值就知道已填写的
        ArrayList<Long> done = new ArrayList<>(todo);
        done.removeAll(empty);

        return result;
    }
}



