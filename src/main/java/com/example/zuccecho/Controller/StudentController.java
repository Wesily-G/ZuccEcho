package com.example.zuccecho.Controller;

import com.example.zuccecho.DTO.StudentDTO;
import com.example.zuccecho.Entity.QAModel;
import com.example.zuccecho.Entity.Student;
import com.example.zuccecho.QueueManager.Constants;
import com.example.zuccecho.QueueManager.ZuccEchoMessage;
import com.example.zuccecho.Repository.StudentRepository;
import com.example.zuccecho.Services.StudentServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;

@RestController
@RequestMapping("student")
@ResponseBody
public class StudentController {
    @Autowired
    private StudentServices studentServices;
    @Autowired
    private AmqpTemplate mqService;
    @Autowired
    private TopicExchange topicExchange;

    @PostMapping(value="addStudent",produces = "application/json;charset=UTF-8")
    public ResponseData addStudent(@RequestBody StudentDTO studentDTO){
        ResponseData rsp = new ResponseData();
        try{
            studentServices.addStudent(studentDTO);
            rsp.setRspData(studentDTO);
        }catch (Exception e){
            if(e instanceof BindException){
                rsp.setError();
                rsp.setRspData("BindException");
            }else{
                rsp.setFailed();
                rsp.setRspData(e.getMessage());
                e.printStackTrace();
            }
        }
        ZuccEchoMessage msg = new ZuccEchoMessage(ZuccEchoMessage.CATEGORY_MODEL_PUB);
        msg.appendContent("rspData",rsp);
        mqService.convertAndSend(Constants.QUE_WORK_QUEUE,msg);
        mqService.convertAndSend(topicExchange.getName(),"zucc.student.31901029",msg);
        return rsp;
    }

    @DeleteMapping("deleteStudent/{id}")
    public ResponseData deleteStudentById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            studentServices.deleteStudentById(id);
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

    @GetMapping("findStudent/{id}")
    public ResponseData findStudentById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            Student student = studentServices.findStudentById(id);
            rsp.setRspData(student);
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

    @PutMapping(value = "updateStudent",produces = "application/json;charset=UTF-8")
    public ResponseData updateStudent(@RequestBody StudentDTO studentDTO){
        ResponseData rsp = new ResponseData();
        try {
            studentServices.updateStudent(studentDTO);
            rsp.setRspData(studentDTO);
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

    @GetMapping("fillStudent")
    public void fillStudent(){
        studentServices.fillStudent();
    }

}