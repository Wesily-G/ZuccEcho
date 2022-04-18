package com.example.zuccecho.Controller;

import com.example.zuccecho.DTO.QAModelDTO;
import com.example.zuccecho.Entity.QAModel;
import com.example.zuccecho.Services.QAModelServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;
import java.util.ArrayList;

@RestController
public class QAModelController {
    @Autowired
    private QAModelServices qaModelServices;

    @PostMapping(value="addModel",produces = "application/json;charset=UTF-8")
    public ResponseData addModel(@RequestBody QAModelDTO qaModelDTO){
        ResponseData rsp = new ResponseData();
        try{
            qaModelServices.addModel(qaModelDTO);
            rsp.setRspData(qaModelDTO);
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

    @DeleteMapping("deleteModel/{id}")
    public ResponseData deleteModelById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            qaModelServices.deleteModelById(id);
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

    @GetMapping("findModel/{id}")
    public ResponseData findModelById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            QAModel model = qaModelServices.findModelById(id);
            rsp.setRspData(model);
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

    @PutMapping(value = "updateModel",produces = "application/json;charset=UTF-8")
    public ResponseData updateModel(@RequestBody QAModelDTO qaModelDTO){
        ResponseData rsp = new ResponseData();
        try {
            qaModelServices.updateModel(qaModelDTO);
            rsp.setRspData(qaModelDTO);
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
}
