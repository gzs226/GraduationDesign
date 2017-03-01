package com.example.graduationdesign.utils.model;

public class Question_bank_field {
    public static final String DATABASENAME = "graduation_design.db";
    public static final int DATABASEVERSION = 1;
    public static final String TABLENAME = "question_bank";

    public static String id = "id";
    public static String question_id = "question_id";
    public static String subject = "subject";//科目
    public static String chapter = "chapter";//章节
    public static String question = "question";//题目问题
    public static String question_number = "question_number";//题目的题号
    public static String option_a = "option_a";//选项一
    public static String option_b = "option_b";
    public static String option_c = "option_c";
    public static String option_d = "option_d";
    public static String answer = "answer";//答案
    public static String difficulty_level = "difficulty_level";//困难程度
    public static String comment_number = "comment_number";//评论数量
    public static String answer_analysis = "answer_analysis";//解析
    public static String user_do = "user_do";//用户是否做了此题
    public static String user_answer = "user_answer";//用户的答案
}
