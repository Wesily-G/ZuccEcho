package com.example.zuccecho.Repository;

import com.example.zuccecho.Entity.QAModelElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.ArrayList;

public interface ModelElasticRepository extends ElasticsearchRepository<QAModelElastic, Long> {
    ArrayList<QAModelElastic> findAllByQuestionsContains(String content);
}
