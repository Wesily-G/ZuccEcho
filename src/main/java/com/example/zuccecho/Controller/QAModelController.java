package com.example.zuccecho.Controller;

import com.example.zuccecho.Entity.QAModel;
import com.example.zuccecho.Repository.QAModelRepository;
import com.example.zuccecho.Services.QAModelControllerServices;
import com.example.zuccecho.Support.ResponseData;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.net.BindException;
import java.util.ArrayList;

@RestController
public class QAModelController implements QAModelControllerServices {
    @Autowired
    private QAModelRepository modelRepository;

    @PostMapping(value="addModel",produces = "application/json;charset=UTF-8")
    public ResponseData addModel(@RequestBody QAModel qaModel){
        ResponseData rsp = new ResponseData();
        try {
            modelRepository.save(qaModel);
            rsp.setRspData(qaModel);
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

    @DeleteMapping("deleteModel/{id}")
    public ResponseData deleteModelById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();

        try{
            modelRepository.deleteById(id);
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

    @GetMapping("findModel/{id}")
    public ResponseData findModelById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();

        try{
            QAModel model = modelRepository.findById(id).get();
            rsp.setRspData(model);
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

    @PutMapping(value = "updateModel",produces = "application/json;charset=UTF-8")
    public ResponseData updateModel(@RequestBody QAModel model){
        ResponseData rsp = new ResponseData();
        try {
            modelRepository.save(model);
            rsp.setRspData(model);
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

    @PostMapping("fillModel")
    public void fillModel(){
        QAModel b = new QAModel();
        b.setId(12315435L);
        ArrayList<String> questions = new ArrayList<String>();
        questions.add("Mona is which type of artist?");
        questions.add("You konw what about light?");
        b.setQuestions(questions);
        ArrayList<String> selections = new ArrayList<String>();
        selections.add("");
        selections.add("A.a;B.b");
        b.setSelections(selections);
        ArrayList<Integer> types = new ArrayList<Integer>();
        types.add(2);
        types.add(0);
        b.setQuestionsType(types);
        modelRepository.save(b);
    }
}
