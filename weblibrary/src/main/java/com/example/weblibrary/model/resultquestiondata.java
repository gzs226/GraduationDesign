package com.example.weblibrary.model;

/**
 * Created by zy on 2017/2/16.
 */

public class resultquestiondata {

    private int question_id;
    private String question_subject;
    private String question_chapter;
    private String question;
    private int question_number;
    private String question_option_a;
    private String question_option_b;
    private String question_option_c;
    private String question_option_d;
    private int answer;
    private int difficulty_level;
    private int comment_number;
    private String answer_analysis;

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_subject() {
        return question_subject;
    }

    public void setQuestion_subject(String question_subject) {
        this.question_subject = question_subject;
    }

    public String getQuestion_chapter() {
        return question_chapter;
    }

    public void setQuestion_chapter(String question_chapter) {
        this.question_chapter = question_chapter;
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

    public String getQuestion_option_a() {
        return question_option_a;
    }

    public void setQuestion_option_a(String question_option_a) {
        this.question_option_a = question_option_a;
    }

    public String getQuestion_option_b() {
        return question_option_b;
    }

    public void setQuestion_option_b(String question_option_b) {
        this.question_option_b = question_option_b;
    }

    public String getQuestion_option_c() {
        return question_option_c;
    }

    public void setQuestion_option_c(String question_option_c) {
        this.question_option_c = question_option_c;
    }

    public String getQuestion_option_d() {
        return question_option_d;
    }

    public void setQuestion_option_d(String question_option_d) {
        this.question_option_d = question_option_d;
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

}
