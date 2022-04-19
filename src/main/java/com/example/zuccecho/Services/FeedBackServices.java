package com.example.zuccecho.Services;

import com.example.zuccecho.DTO.FeedbackDTO;
import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Entity.Feedback;
import com.example.zuccecho.Support.ResponseData;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;

public interface FeedBackServices {
    AnswerSheet checkSpecificContent(long answersheetID);
    Feedback findFeedback(Long feedbackID);
    HashMap<String,ArrayList<Long>> feedbackStatistics(long feedbackID);
    Feedback publicFeedback(FeedbackDTO feedbackDTO);
    void ReminderForNotFilled(long feedbackID);
    void fillAnswerSheet();
    void fillFeedback();
}
