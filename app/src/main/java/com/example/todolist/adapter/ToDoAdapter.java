package com.example.todolist.adapter;

import androidx.recyclerview.widget.RecyclerView;
import com.example.todolist.MainActivity;
import com.example.todolist.model.ToDoModel;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> toDoList;
    private MainActivity activity;

    public ToDoAdapter(MainActivity activity){
        this.activity = activity;
    }

}
