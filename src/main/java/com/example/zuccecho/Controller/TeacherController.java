package com.example.zuccecho.Controller;

import com.example.zuccecho.DTO.TeacherDTO;
import com.example.zuccecho.Entity.Student;
import com.example.zuccecho.Entity.Teacher;
import com.example.zuccecho.QueueManager.Constants;
import com.example.zuccecho.QueueManager.ZuccEchoMessage;
import com.example.zuccecho.Repository.TeacherRepository;
import com.example.zuccecho.Services.TeacherServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;

@RestController
@RequestMapping("teacher")
@ResponseBody
public class TeacherController {
    @Autowired
    private TeacherServices teacherServices;
    @Autowired
    private AmqpTemplate mqService;
    @Autowired
    private TopicExchange topicExchange;

    @PostMapping(value="addTeacher",produces = "application/json;charset=UTF-8")
    public ResponseData addTeacher(@RequestBody TeacherDTO teacherDTO){
        ResponseData rsp = new ResponseData();
        try{
            teacherServices.addTeacher(teacherDTO);
            rsp.setRspData(teacherDTO);
        }catch (Exception e){
            if(e instanceof BindException){
                rsp.setError();
                rsp.setRspData("BindException");
            }else{
                rsp.setFailed();
                rsp.setRspData(e.getMessage());
            }
        }
        ZuccEchoMessage msg = new ZuccEchoMessage(ZuccEchoMessage.CATEGORY_MODEL_PUB);
        msg.appendContent("rspData",rsp);
        mqService.convertAndSend(topicExchange.getName(),"zucc.teacher.31901031",msg);
        return rsp;
    }

    @DeleteMapping("deleteTeacher/{id}")
    public ResponseData deleteTeacherById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            teacherServices.deleteTeacherById(id);
            rsp.setRspData(new Boolean(Boolean.TRUE));
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

    @GetMapping("findTeacher/{id}")
    public ResponseData findTeacherById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            Teacher teacher = teacherServices.findTeacherById(id);
            rsp.setRspData(teacher);
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

    @PutMapping(value = "updateTeacher",produces = "application/json;charset=UTF-8")
    public ResponseData updateTeacher(@RequestBody TeacherDTO teacherDTO){
        ResponseData rsp = new ResponseData();
        try {
            teacherServices.updateTeacher(teacherDTO);
            rsp.setRspData(teacherDTO);
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

    @GetMapping("fillTeacher")
    public void fillTeacher(){
        teacherServices.fillTeacher();
    }

}
