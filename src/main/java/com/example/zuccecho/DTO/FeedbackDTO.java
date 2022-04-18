package com.example.zuccecho.DTO;

import com.example.zuccecho.Support.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Data
public class FeedbackDTO implements Serializable {
    private final Long classID;
    private final Long TeacherID;
    private final ArrayList<Long> answersheetsID;
    private final Date endDate;

}