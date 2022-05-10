package com.example.zuccecho.Controller;

import com.example.zuccecho.DTO.AnswerSheetDTO;
import com.example.zuccecho.Entity.AnswerSheet;
import com.example.zuccecho.Entity.AnswerSheetElastic;
import com.example.zuccecho.Entity.QAModelElastic;
import com.example.zuccecho.QueueManager.Constants;
import com.example.zuccecho.QueueManager.ZuccEchoMessage;
import com.example.zuccecho.Repository.AnswerSheetRepository;
import com.example.zuccecho.Repository.AnswersheetElasticRepository;
import com.example.zuccecho.Repository.FeedbackRepository;
import com.example.zuccecho.Services.AnalysisServices;
import com.example.zuccecho.Services.FeedBackServices;
import com.example.zuccecho.Services.QAModelServices;
import com.example.zuccecho.Services.StudentAnswerServices;
import com.example.zuccecho.Support.ResponseData;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("studentAnswer")
@ResponseBody
public class StudentAnswerController {
    @Autowired
    private StudentAnswerServices answerServices;
    @Autowired
    private AmqpTemplate mqService;
    @Autowired
    private AnswersheetElasticRepository aer;

    //create new AnswerSheet
    @PostMapping(value="writeAnswerSheet",produces = "application/json;charset=UTF-8")
    public ResponseData writeAnswerSheet(@RequestBody AnswerSheetDTO answerSheetDTO){
        ResponseData rsp = new ResponseData();
        try{
            answerServices.writeAnswerSheet(answerSheetDTO);
            rsp.setRspData(answerSheetDTO);
        }catch (Exception e){
            if(e instanceof BindException){
                rsp.setError();
                rsp.setRspData("BindException");
            }else{
                rsp.setFailed();
                rsp.setRspData(new Boolean(Boolean.FALSE));
            }
        }
        ZuccEchoMessage msg = new ZuccEchoMessage(ZuccEchoMessage.CATEGORY_ANSWER);
        msg.appendContent("rspData",rsp);
        mqService.convertAndSend(Constants.QUE_WORK_QUEUE,msg);
        mqService.convertSendAndReceive(Constants.QUE_RPC_QUEUE,msg);
        return rsp;
    }

    public ResponseData checkHistoricalAnswerList(long studentID,long feedbackID){
        ResponseData rsp = new ResponseData();

        try{
            ArrayList<AnswerSheet> answers = new ArrayList<AnswerSheet>();
            answers = answerServices.checkHistoricalAnswerList(studentID,feedbackID);
            rsp.setRspData(answers);
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

    @PostMapping("fillElastic")
    public void fillElasticAnswersheet(){
        AnswerSheetElastic t = new AnswerSheetElastic();
        ArrayList<String> tlist = new ArrayList<String>();
        t.setId(new Random().nextLong());
        tlist.add("tiequanainiou");
        tlist.add("啦啦啦德玛西亚");
        t.setAnswers(tlist);
        aer.save(t);
    }

    @GetMapping("search")
    public ResponseData searchByPageAndSort(Integer start, String key) {
        ResponseData rsp = new ResponseData();
        ArrayList<AnswerSheetElastic> result = new ArrayList<>();
        // 分页：
        if (start == null) {
            start = 0;
        }
        int size = 2;//每页文档数
        // 构建查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilderQueryBuilder = new NativeSearchQueryBuilder();
        // nativeSearchQueryBuilderQueryBuilder.withQuery(QueryBuilders.matchQuery("name", key));
        nativeSearchQueryBuilderQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(key, "answers"));
        //nativeSearchQueryBuilderQueryBuilder.withHighlightFields(new HighlightBuilder.Field("name").preTags("<span style='background-color: #FFFF00'>").postTags("</span>"));
        // 搜索，获取结果
        // nativeSearchQueryBuilderQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        nativeSearchQueryBuilderQueryBuilder.withPageable(PageRequest.of(start, size));
        Page<AnswerSheetElastic> outputs = aer.search(nativeSearchQueryBuilderQueryBuilder.build());
        // 总条数
        for (AnswerSheetElastic output : outputs) {
            System.out.println(output);
            result.add(output);
        }
        rsp.setRspData(result);
        return rsp;
    }
}
