package com.example.zuccecho.Quartz;

import com.example.zuccecho.Services.FeedBackServices;
import com.example.zuccecho.Services.implement.FeedBackServicesImplement;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class ReminderForNotFilledSchedule {
    public static void schedule(long feedbackId){
        try {
            //创建job
            JobDetail jobDetail = JobBuilder.newJob(ReminderForNotFilledJob.class)
                    .withIdentity("reminderForNotFilled")
                    .usingJobData("feedbackId",feedbackId)
                    .build();

            //创建触发器trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("reminderForNotFilled")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(2).repeatForever())
                    .usingJobData("feedbackId",feedbackId)
                    .build();

            //创建scheduler，将上述两者绑定然后运行
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();

            //判断是否到了结束时间，超过之后中断作业
            FeedBackServices feedBackServices = new FeedBackServicesImplement();
            while(true){
                Date nowTime = new Date();
                Date endTIme = feedBackServices.findFeedback(feedbackId).getEndDate();
                if(nowTime.getTime()>=endTIme.getTime()){
                    scheduler.shutdown();
                    System.out.println("Feedback"+feedbackId+" is finished");
                    break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
