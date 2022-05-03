package com.example.zuccecho.QueueManager;

/**
 * @author pengbin
 * @version 1.0
 */
public class Constants {
    public static final String Q_CATEGORY_SUBJECTIVE = "subjective";
    public static final String Q_CATEGORY_SINGLE_CHOICE = "single";
    public static final String Q_CATEGORY_MULTI_CHOICE = "multi";

    public static final String TEMPLATE_STATUS_NORMAL = "normal";

    public static final String PAPER_STATUS_PUBLISHED = "published";

    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";

    public static final String QUE_SIMPLE = "simple";
    public static final String QUE_WORK_QUEUE= "work-queue";
    public static final String QUE_RPC_QUEUE= "rpc-queue";

    public static final String EX_FANOUT = "ex-fanout";
    public static final String EX_DIRECT = "ex-direct";
    public static final String EX_TOPIC = "ex-topic";
    public static final String EX_RPC = "ex-rpc";

    public static final String KEY_FATAL = "fatal";
    public static final String KEY_WARN = "warn";
    public static final String KEY_RPC = "rpc";

    public static final String TOPIC_TEACHER = "*.teacher.*";
    public static final String TOPIC_STUDENT = "*.student.*";
}
