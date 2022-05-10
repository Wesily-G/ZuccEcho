package com.example.zuccecho.Controller;

import com.example.zuccecho.DTO.QAModelDTO;
import com.example.zuccecho.Entity.QAModel;
import com.example.zuccecho.Entity.QAModelElastic;
import com.example.zuccecho.Repository.ModelElasticRepository;
import com.example.zuccecho.Services.QAModelServices;
import com.example.zuccecho.Support.ResponseData;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.BindException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("model")
@ResponseBody
public class QAModelController {
    @Autowired
    private QAModelServices qaModelServices;
    @Autowired
    private ModelElasticRepository mer;

    @PostMapping(value="addModel",produces = "application/json;charset=UTF-8")
    public ResponseData addModel(@RequestBody QAModelDTO qaModelDTO){
        ResponseData rsp = new ResponseData();
        try{
            qaModelServices.addModel(qaModelDTO);
            rsp.setRspData(qaModelDTO);
        }catch (Exception e){
            if(e instanceof BindException){
                rsp.setError();
                rsp.setRspData("BindException");
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }
        return rsp;
    }

    @DeleteMapping("deleteModel/{id}")
    public ResponseData deleteModelById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            qaModelServices.deleteModelById(id);
            rsp.setRspData(new Boolean(Boolean.TRUE));
        }catch (Exception e){
            if(e instanceof MethodArgumentNotValidException){
                rsp.setError();
                rsp.setRspData("MethodArgumentNotValidException");
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }

        return rsp;

    }

    @GetMapping("findModel/{id}")
    public ResponseData findModelById(@PathVariable("id") Long id){
        ResponseData rsp = new ResponseData();
        try{
            QAModel model = qaModelServices.findModelById(id);
            rsp.setRspData(model);
        }catch (Exception e){
            if(e instanceof MethodArgumentNotValidException){
                rsp.setError();
                rsp.setRspData("MethodArgumentNotValidException");
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }

        return rsp;
    }

    @PutMapping(value = "updateModel",produces = "application/json;charset=UTF-8")
    public ResponseData updateModel(@RequestBody QAModelDTO qaModelDTO){
        ResponseData rsp = new ResponseData();
        try {
            qaModelServices.updateModel(qaModelDTO);
            rsp.setRspData(qaModelDTO);
        }catch (Exception e){
            if(e instanceof BindException){
                rsp.setError();
                rsp.setRspData("BindException");
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }
        return rsp;
    }

    @PostMapping("fillElastic")
    public void fillElasticModel(){
        QAModelElastic t = new QAModelElastic();
        ArrayList<String> tlist = new ArrayList<String>();
        t.setId(new Random().nextLong());
        tlist.add("shanggouquan");
        tlist.add("上海龙暴打首尔王朝");
        t.setQuestions(tlist);
        mer.save(t);
    }

    @GetMapping("search")
    public ResponseData searchByPageAndSort(Integer start, String key) {
        ResponseData rsp = new ResponseData();
        ArrayList<QAModelElastic> result = new ArrayList<>();
        // 分页：
        if (start == null) {
            start = 0;
        }
        int size = 2;//每页文档数
        // 构建查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilderQueryBuilder = new NativeSearchQueryBuilder();
        // nativeSearchQueryBuilderQueryBuilder.withQuery(QueryBuilders.matchQuery("name", key));
        nativeSearchQueryBuilderQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(key, "questions"));
        //nativeSearchQueryBuilderQueryBuilder.withHighlightFields(new HighlightBuilder.Field("name").preTags("<span style='background-color: #FFFF00'>").postTags("</span>"));
        // 搜索，获取结果
        // nativeSearchQueryBuilderQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        nativeSearchQueryBuilderQueryBuilder.withPageable(PageRequest.of(start, size));
        Page<QAModelElastic> outputs = mer.search(nativeSearchQueryBuilderQueryBuilder.build());
        // 总条数
        for (QAModelElastic output : outputs) {
            System.out.println(output);
            result.add(output);
        }
        rsp.setRspData(result);
        return rsp;
    }

}
