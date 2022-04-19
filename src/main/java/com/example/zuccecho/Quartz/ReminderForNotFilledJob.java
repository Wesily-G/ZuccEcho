package com.example.zuccecho.Quartz;

import com.example.zuccecho.Controller.FeedBackController;
import com.example.zuccecho.Services.FeedBackServices;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ReminderForNotFilledJob implements Job {
    private FeedBackServices feedBackServices;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        long feedbackId = jobDataMap.getLong("feedbackId");
        HashMap<String,ArrayList<Long>> result =  feedBackServices.feedbackStatistics(feedbackId);
        System.out.println("ReminderForNotFilled(Job):"+sdf.format(date));
        if(result.get("empty")!=null){
            System.out.println("There are someone who not filled:"+result.get("empty"));
        }else{
            System.out.println("feedback" + feedbackId + "is finished");
        }
    }
}
