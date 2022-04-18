package com.example.zuccecho.Services;

import com.example.zuccecho.Entity.Lecture;
import com.example.zuccecho.Support.ResponseData;

import java.util.Date;

public interface LectureControllerServices {
    public ResponseData creatLecture(Lecture lecture);
    public ResponseData closeLecture(Long lectureID);
    public ResponseData deleteLecture(Long lectureID);
    public ResponseData updateClass(Lecture lec);
}
