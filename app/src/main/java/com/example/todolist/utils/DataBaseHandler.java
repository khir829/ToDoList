package com.example.todolist.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todolist.model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String name = "toDoListDatabase";
    private static final String toDoTable = "todo";
    private static final String ID = "id";
    private static final String task = "task";
    private static final String status = "status";
    private static final String createToDoTable = "CREATE TABLE " + toDoTable + "(" + ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + task + " TEXT, " + status + " INTEGER)";


    private SQLiteDatabase db;

    public DataBaseHandler(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createToDoTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old tables
        db.execSQL("DROP TABLE IF EXISTS " + toDoTable);
        // Create tables again
        onCreate(db);
    }

    public void openDataBase() {
        db = this.getWritableDatabase();
    }

    public void insertTask(ToDoModel tasks){
        ContentValues cv = new ContentValues();
        cv.put(task, tasks.getTask());
        cv.put(status, 0);
        db.insert(toDoTable, null, cv);
    }

    public List<ToDoModel> getAllTasks(){
        List<ToDoModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(toDoTable, null, null, null, null, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        ToDoModel task = new ToDoModel();
                        task.setId(cur.getInt(cur.getColumnIndexOrThrow(ID)));
                        task.setTask(cur.getString(cur.getColumnIndexOrThrow(this.task)));
                        task.setStatus(cur.getInt(cur.getColumnIndexOrThrow(status)));
                        taskList.add(task);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return taskList;
    }

    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(this.status, status);
        db.update(toDoTable, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void updateTask(int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(this.task, task);
        db.update(toDoTable, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void deleteTask(int id){
        db.delete(toDoTable, ID + "= ?", new String[] {String.valueOf(id)});
    }
}