package com.example.zuccecho.Services;

import com.example.zuccecho.DTO.QAModelDTO;
import com.example.zuccecho.Entity.QAModel;
import com.example.zuccecho.Support.ResponseData;

import java.util.ArrayList;

public interface QAModelServices {
    public QAModel addModel(QAModelDTO qaModelDTO);
    public void deleteModelById(Long id);
    public QAModel findModelById(Long id);
    public boolean updateModel(QAModelDTO modelDTO);

}
