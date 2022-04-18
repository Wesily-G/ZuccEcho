package com.example.zuccecho.Services;

import com.example.zuccecho.DTO.LectureDTO;
import com.example.zuccecho.Entity.Lecture;
import com.example.zuccecho.Support.ResponseData;

import java.util.Date;

public interface LectureServices {
    public Lecture creatLecture(LectureDTO lectureDTO);
    public void closeLecture(Long lectureID);
    public void deleteLecture(Long lectureID);
    public boolean updateLecture(LectureDTO lectureDTO);
    public Lecture findLecture(Long lectureID);
}
