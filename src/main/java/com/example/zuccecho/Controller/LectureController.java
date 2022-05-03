package com.example.zuccecho.Controller;
import com.example.zuccecho.DTO.LectureDTO;
import com.example.zuccecho.Services.LectureServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;

@RestController
@RequestMapping("lecture")
@ResponseBody
public class LectureController {
    @Autowired
    private LectureServices lectureServices;

    //要求前端传入UTF-8的Json格式数据，并且也不会通过URL传入参数
    @PostMapping(value="creatLecture",produces = "application/json;charset=UTF-8")
    public ResponseData creatLecture(@RequestBody LectureDTO lectureDTO){
        ResponseData rsp = new ResponseData();
        try{
            lectureServices.creatLecture(lectureDTO);
            rsp.setRspData(lectureDTO);
        }catch (Exception e){
            if(e instanceof BindException){
                rsp.setError();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }else{
                rsp.setFailed();
                rsp.setRspData(e.getMessage());
                e.printStackTrace();
            }
        }
        return rsp;
    }

    @PutMapping("closeLecture/{id}")
    public ResponseData closeLecture(@PathVariable("id") Long lectureID){
        ResponseData rsp = new ResponseData();
        try{
            lectureServices.closeLecture(lectureID);
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
            lectureServices.deleteLecture(lectureID);
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

    @PutMapping(value = "updateLecture",produces = "application/json;charset=UTF-8")
    //0为修改，1为添加
    public ResponseData updateClass(@RequestBody LectureDTO lectureDTO){
        ResponseData rsp = new ResponseData();
        try{
            lectureServices.updateLecture(lectureDTO);
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

}
