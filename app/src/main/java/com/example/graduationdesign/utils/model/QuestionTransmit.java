package com.example.graduationdesign.utils.model;

import java.io.Serializable;

/**
 * Created by gg on 2017/2/16.
 */

public class QuestionTransmit implements Serializable {

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

    public final int getQuestion_id() {
        return question_id;
    }

    public final void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public final String getSubject() {
        return subject;
    }

    public final void setSubject(String subject) {
        this.subject = subject;
    }

    public final String getChapter() {
        return chapter;
    }

    public final void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public final String getQuestion() {
        return question;
    }

    public final void setQuestion(String question) {
        this.question = question;
    }

    public final int getQuestion_number() {
        return question_number;
    }

    public final void setQuestion_number(int question_number) {
        this.question_number = question_number;
    }

    public final String getOption_a() {
        return option_a;
    }

    public final void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public final String getOption_b() {
        return option_b;
    }

    public final void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public final String getOption_c() {
        return option_c;
    }

    public final void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public final String getOption_d() {
        return option_d;
    }

    public final void setOption_d(String option_d) {
        this.option_d = option_d;
    }

    public final int getAnswer() {
        return answer;
    }

    public final void setAnswer(int answer) {
        this.answer = answer;
    }

    public final int getDifficulty_level() {
        return difficulty_level;
    }

    public final void setDifficulty_level(int difficulty_level) {
        this.difficulty_level = difficulty_level;
    }

    public final int getComment_number() {
        return comment_number;
    }

    public final void setComment_number(int comment_number) {
        this.comment_number = comment_number;
    }

    public final String getAnswer_analysis() {
        return answer_analysis;
    }

    public final void setAnswer_analysis(String answer_analysis) {
        this.answer_analysis = answer_analysis;
    }

    public final int getUser_do() {
        return user_do;
    }

    public final void setUser_do(int user_do) {
        this.user_do = user_do;
    }

    public final int getUser_answer() {
        return user_answer;
    }

    public final void setUser_answer(int user_answer) {
        this.user_answer = user_answer;
    }
}
