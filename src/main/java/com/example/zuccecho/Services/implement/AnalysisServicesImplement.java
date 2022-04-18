package com.example.zuccecho.Services.implement;

import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Repository.AnswerSheetRepository;
import com.example.zuccecho.Services.AnalysisServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class AnalysisServicesImplement implements AnalysisServices {

    //...(暂时不需要实现)

    @Autowired
    private AnswerSheetRepository asr;

    public ArrayList<AnswerSheet> getAnswerSheets(long feedbackID){
        //...

        return null;
    }

    public HashMap<String, Map<String,Integer>> analyzeChoices(long feedbackID){

        //...

        return null;


    }

    public HashMap<String,ArrayList<String>> analyzeCompletion(long feedbackID){

        //...

        return null;
    }

    public HashMap<String,Integer> wordStastic(ArrayList<String> answers){

        //...

        return null;
    }

}
