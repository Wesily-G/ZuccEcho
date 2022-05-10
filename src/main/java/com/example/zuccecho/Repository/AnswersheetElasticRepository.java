package com.example.zuccecho.Repository;

import com.example.zuccecho.Entity.AnswerSheetElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AnswersheetElasticRepository extends ElasticsearchRepository<AnswerSheetElastic, Long> {
}
