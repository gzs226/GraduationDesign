package com.example.graduationdesign.utils.model;

public class Question_bank {
    //int question_id = 0;
    int question_id = 1;
    String subject = "数学";//科目
    String chapter = "章节"; //章节
    String question = "问题"; //章节
    int question_number = 10;//题目的题号
    String option_a = "option_a";//选项一
    String option_b = "option_b";
    String option_c = "option_c";
    String option_d = "option_d";
    int answer = 1;//答案
    int difficulty_level = 5;//困难程度
    int comment_number = 100;//评论数量
    String answer_analysis = "答案解析";//解析
    int user_do = 0;
    int user_answer = 3;

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(int question_number) {
        this.question_number = question_number;
    }

    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public String getOption_d() {
        return option_d;
    }

    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getDifficulty_level() {
        return difficulty_level;
    }

    public void setDifficulty_level(int difficulty_level) {
        this.difficulty_level = difficulty_level;
    }

    public int getComment_number() {
        return comment_number;
    }

    public void setComment_number(int comment_number) {
        this.comment_number = comment_number;
    }

    public String getAnswer_analysis() {
        return answer_analysis;
    }

    public void setAnswer_analysis(String answer_analysis) {
        this.answer_analysis = answer_analysis;
    }

    public int getUser_do() {
        return user_do;
    }

    public void setUser_do(int user_do) {
        this.user_do = user_do;
    }

    public int getUser_answer() {
        return user_answer;
    }

    public void setUser_answer(int user_answer) {
        this.user_answer = user_answer;
    }
}
