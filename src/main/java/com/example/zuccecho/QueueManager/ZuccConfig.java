package com.example.zuccecho.QueueManager;

import com.example.zuccecho.QueueManager.Consumer.WorkQueueConsumer;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuccConfig {
    //基础模式
    @Bean
    public Queue queue() {
        return new Queue(Constants.QUE_SIMPLE);
    }

    //工作队列模式，因为需要多个想通过的消费者所以这里就不能用component
    @Bean
    public Queue queueWorkQueue() {
        return new Queue(Constants.QUE_WORK_QUEUE);
    }
    @Bean
    public WorkQueueConsumer workQueueConsumer01() {
        return new WorkQueueConsumer("Worker-01");
    }
    @Bean
    public WorkQueueConsumer workQueueConsumer02() {
        return new WorkQueueConsumer("Worker-02");
    }

    //发布订阅模式,路由只需要和队列绑定，消费者可以自由监听队列，所以消费者直接component注释注入了
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange(Constants.EX_FANOUT);
    }
    @Bean
    public Queue fanoutTrackQue() { return new AnonymousQueue(); }
    @Bean
    public Queue fanoutNotifyQue() {
        return new AnonymousQueue();
    }
    @Bean
    public Binding bindingNotify(FanoutExchange fanout,
                                 Queue fanoutNotifyQue) {
        return BindingBuilder.bind(fanoutNotifyQue).to(fanout);
    }
    @Bean
    public Binding bindingTrack(FanoutExchange fanout,
                                Queue fanoutTrackQue) {
        return BindingBuilder.bind(fanoutTrackQue).to(fanout);
    }

    //广播路由模式，同理，消费者注入了，注意Qualifier标签，下面有解释
    @Bean
    public DirectExchange direct() {
        return new DirectExchange(Constants.EX_DIRECT);
    }
    @Bean
    public Queue directFatalQue() {
        return new AnonymousQueue();
    }
    @Bean
    public Queue directWarnQue() {
        return new AnonymousQueue();
    }
    @Bean
    public Binding bindingFatal(@Qualifier("direct") DirectExchange direct,
                                Queue directFatalQue) {
        return BindingBuilder.bind(directFatalQue)
                .to(direct)
                .with(Constants.KEY_FATAL);
    }
    @Bean
    public Binding bindingWarn(@Qualifier("direct") DirectExchange direct,
                               Queue directWarnQue) {
        return BindingBuilder.bind(directWarnQue)
                .to(direct)
                .with(Constants.KEY_WARN);
    }

    //主题模式,同理，消费者自动注入了
    @Bean
    public TopicExchange topic() {
        return new TopicExchange(Constants.EX_TOPIC);
    }
    @Bean
    public Queue topicTeacherStatQue() {
        return new AnonymousQueue();
    }
    @Bean
    public Queue topicStudentStatQue() {
        return new AnonymousQueue();
    }
    @Bean
    public Binding bindingTeacher(TopicExchange topic,
                               Queue topicTeacherStatQue) {
        return BindingBuilder.bind(topicTeacherStatQue)
                .to(topic)
                .with(Constants.TOPIC_TEACHER);
    }
    @Bean
    public Binding bindingStudent(TopicExchange topic,
                                 Queue topicStudentStatQue) {
        return BindingBuilder.bind(topicStudentStatQue)
                .to(topic)
                .with(Constants.TOPIC_STUDENT);
    }

    //RPC模式,同理，消费者自动注入
    @Bean
    public DirectExchange rpc() {
        return new DirectExchange(Constants.EX_RPC);
    }
    @Bean
    public Queue rpcQue() {
        return new Queue(Constants.QUE_RPC_QUEUE);
    }
    //qualifier标签用于特定注入对象,前面有相同类型的路由所以这里需要指定
    @Bean
    public Binding bindingRpc(@Qualifier("rpc") DirectExchange rpc,
                              Queue rpcQue) {
        return BindingBuilder.bind(rpcQue)
                .to(rpc)
                .with(Constants.KEY_RPC);
    }


}
