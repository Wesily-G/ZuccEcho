package com.example.zuccecho.Services;

import com.example.zuccecho.DTO.FeedbackDTO;
import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Entity.Feedback;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;

public interface FeedBackServices {
    public AnswerSheet checkSpecificContent(long answersheetID);
    public Feedback findFeedback(Long feedbackID);
    public HashMap<String, ArrayList<String>> feedbackStatistics(long feedbackID);
    public Feedback publicFeedback(FeedbackDTO feedbackDTO);
    public void fillAnswerSheet();
    public void fillFeedback();
}
