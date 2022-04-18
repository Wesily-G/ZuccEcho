package com.example.zuccecho.Services;

import com.example.zuccecho.Entity.AnswerSheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface AnalysisControllerServices {
    public ArrayList<AnswerSheet> getAnswerSheets(long feedbackID);
    public HashMap<String, Map<String,Integer>> analyzeChoices(long feedbackID);
    public HashMap<String,ArrayList<String>> analyzeCompletion(long feedbackID);
    public HashMap<String,Integer> wordStastic(ArrayList<String> answers);
}
