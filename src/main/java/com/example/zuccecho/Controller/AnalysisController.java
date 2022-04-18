package com.example.zuccecho.Controller;

import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Repository.AnswerSheetRepository;
import com.example.zuccecho.Services.AnalysisControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AnalysisController implements AnalysisControllerServices {

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
