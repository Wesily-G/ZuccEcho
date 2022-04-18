package com.example.zuccecho.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LectureDTO implements Serializable {
    private final Long id;
    private final String Name;
    private final String area;
    private final Date time;
    private final Long teacherID;
    private final boolean isOpen;
}