package com.example.zuccecho.Services;

import com.example.zuccecho.Support.ResponseData;

import java.util.ArrayList;

public interface StudentAnswerControllerServices {
    public ResponseData writeAnswerSheet(ArrayList<String> list, Long studentID);
    public ResponseData checkHistoricalAnswerList(long studentID,long feedbackID);
}
