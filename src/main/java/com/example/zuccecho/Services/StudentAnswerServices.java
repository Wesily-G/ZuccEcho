package com.example.zuccecho.Services;

import com.example.zuccecho.DTO.AnswerSheetDTO;
import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Support.ResponseData;

import java.util.ArrayList;

public interface StudentAnswerServices {
    public AnswerSheet writeAnswerSheet(AnswerSheetDTO answerSheetDTO);
    public ArrayList<AnswerSheet> checkHistoricalAnswerList(long studentID,long feedbackID);
}
