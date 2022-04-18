package com.example.zuccecho.Controller;
import com.example.zuccecho.Entity.Lecture;
import com.example.zuccecho.Repository.LectureRepository;
import com.example.zuccecho.Services.LectureControllerServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.net.BindException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("lecture")
public class LectureController implements LectureControllerServices {
    @Autowired
    private LectureRepository lr;

    //要求前端传入UTF-8的Json格式数据，并且也不会通过URL传入参数
    @PostMapping(value="creatLecture",produces = "application/json;charset=UTF-8")
    public ResponseData creatLecture(@RequestBody Lecture lecture){
        ResponseData rsp = new ResponseData();
        try{
            lr.save(lecture);
            rsp.setRspData(lecture);
        }catch (Exception e){
            if(e instanceof BindException){
                rsp.setError();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }
        return rsp;
    }

    @PutMapping("closeLecture/{id}")
    public ResponseData closeLecture(@PathVariable("id") Long lectureID){
        ResponseData rsp = new ResponseData();

        try{
            Lecture lecture = lr.findById(lectureID).get();
            lecture.setOpen(false);
            lr.save(lecture);
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

    @DeleteMapping("deleteLecture/{id}")
    public ResponseData deleteLecture(@PathVariable("id") Long lectureID){
        ResponseData rsp = new ResponseData();

        try{
            Lecture lecture = lr.findById(lectureID).get();
            if(lecture.getId().equals(lectureID)){
                lr.deleteById(lectureID);
                rsp.setRspData(new Boolean(Boolean.TRUE));
            }
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

    @PutMapping(value = "updateLecture",produces = "application/json;charset=UTF-8")
    //0为修改，1为添加
    public ResponseData updateClass(@RequestBody Lecture lec){
        ResponseData rsp = new ResponseData();

        try{
            lr.save(lec);
            rsp.setRspData(new Boolean(Boolean.TRUE));
        }catch (Exception e){
            if(e instanceof BindException){
                rsp.setError();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }

        return rsp;
    }

    //填充测试不设置response
    @PostMapping("fillLecture")
    public void fillLecture(){
        Lecture a = new Lecture();
        a.setName("数据结果");
        a.setArea("理4");
        a.setId(12344l);
        lr.save(a);
    }

}
