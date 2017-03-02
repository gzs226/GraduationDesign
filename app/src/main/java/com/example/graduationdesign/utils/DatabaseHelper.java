package com.example.graduationdesign.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.graduationdesign.utils.model.Question_bank_field;


public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, Question_bank_field.DATABASENAME, null, Question_bank_field.DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_bank = "CREATE TABLE " + Question_bank_field.BANK_TABLENAME + " ("
                + Question_bank_field.id + " INTEGER  PRIMARY KEY AUTOINCREMENT ,"//id为主键，且自增
                + Question_bank_field.question_id + " INTEGER NOT NULL,"//id为主键，且自增
                + Question_bank_field.subject + " VARCHAR(20) NOT NULL,"//科目
                + Question_bank_field.chapter + " VARCHAR(30) NOT NULL,"//章节
                + Question_bank_field.question + " VARCHAR(80) NOT NULL,"//题目问题
                + Question_bank_field.question_number + " INTEGER NOT NULL,"//题目的题号
                + Question_bank_field.option_a + " VARCHAR(50) NOT NULL,"//选项一
                + Question_bank_field.option_b + " VARCHAR(50) NOT NULL,"
                + Question_bank_field.option_c + " VARCHAR(50) NOT NULL,"
                + Question_bank_field.option_d + " VARCHAR(50) NOT NULL,"
                + Question_bank_field.answer + " INTEGER NOT NULL,"//答案
                + Question_bank_field.difficulty_level + " INTEGER NOT NULL,"//困难程度
                + Question_bank_field.comment_number + " INTEGER NOT NULL,"//评论数量
                + Question_bank_field.answer_analysis + " VARCHAR(50) NOT NULL,"//解析
                + Question_bank_field.user_do + " INTEGER NOT NULL,"//用户是否做了此题
                + Question_bank_field.user_answer + " INTEGER NOT NULL"//用户答案
                + ");";
        db.execSQL(sql_bank);

        String sql_collection = "CREATE TABLE " + Question_bank_field.COLLECTION_TABLENAME + " ("
                + Question_bank_field.id + " INTEGER  PRIMARY KEY AUTOINCREMENT ,"//id为主键，且自增
                + Question_bank_field.question_id + " INTEGER NOT NULL,"//id为主键，
                + Question_bank_field.question + " VARCHAR(80) NOT NULL"//题目问题
                + ");";
        db.execSQL(sql_collection);

        String sql_note = "CREATE TABLE " + Question_bank_field.NOTE_TABLENAME + " ("
                + Question_bank_field.id + " INTEGER  PRIMARY KEY AUTOINCREMENT ,"//id为主键，且自增
                + Question_bank_field.question_id + " INTEGER NOT NULL,"//id为主键，
                + Question_bank_field.question + " VARCHAR(80) NOT NULL,"//题目问题
                + Question_bank_field.note_text + " VARCHAR(50) NOT NULL"//解析
                + ");";
        db.execSQL(sql_note);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + Question_bank_field.BANK_TABLENAME;
        db.execSQL(sql);
        String sql_bank = "DROP TABLE IF EXISTS " + Question_bank_field.COLLECTION_TABLENAME;
        db.execSQL(sql_bank);
        String sql_collection = "DROP TABLE IF EXISTS " + Question_bank_field.NOTE_TABLENAME;
        db.execSQL(sql_collection);
        this.onCreate(db);
    }
}
