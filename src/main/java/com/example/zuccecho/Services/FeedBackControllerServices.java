package com.example.zuccecho.Services;

import com.example.zuccecho.Support.ResponseData;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;

public interface FeedBackControllerServices {
    public ResponseData checkSpecificContent(long answersheetID);
    public ResponseData findFeedback(Long feedbackID);
    public HashMap<String, ArrayList<String>> feedbackStatistics(long feedbackID);
}
