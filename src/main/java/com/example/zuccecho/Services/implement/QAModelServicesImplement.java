package com.example.zuccecho.Services.implement;

import com.example.zuccecho.DTO.QAModelDTO;
import com.example.zuccecho.Entity.Lecture;
import com.example.zuccecho.Entity.QAModel;
import com.example.zuccecho.Repository.QAModelRepository;
import com.example.zuccecho.Services.QAModelServices;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;
import java.util.ArrayList;

@Service
public class QAModelServicesImplement implements QAModelServices {
    @Autowired
    private QAModelRepository mr;

    public QAModel addModel(QAModelDTO qaModelDTO){
        QAModel model = new QAModel();
        //错误校验后用entity类存入数据库
        BeanUtils.copyProperties(qaModelDTO, model);
        mr.save(model);
        return model;
    }

    public void deleteModelById(Long id){
        mr.deleteById(id);
    }

    public QAModel findModelById(Long id){
        return mr.findById(id).get();
    }

    public boolean updateModel(QAModelDTO qaModelDTO){
        try {
            QAModel qaModel = new QAModel();
            BeanUtils.copyProperties(qaModelDTO,qaModel);
            mr.save(qaModel);
        }catch (Exception e){
            return false;
        }
        return true;
    }

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
        mr.save(b);
    }
}
