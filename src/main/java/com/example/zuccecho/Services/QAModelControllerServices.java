package com.example.zuccecho.Services;

import com.example.zuccecho.Entity.QAModel;
import com.example.zuccecho.Support.ResponseData;

import java.util.ArrayList;

public interface QAModelControllerServices {
    public ResponseData addModel(QAModel qaModel);
    public ResponseData deleteModelById(Long id);
    public ResponseData findModelById(Long id);
    public ResponseData updateModel(QAModel model);

}
