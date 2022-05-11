package com.example.todolist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;
import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.model.ToDoModel;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    private List<ToDoModel> toDoList;
    private MainActivity activity;

    public ToDoAdapter(MainActivity activity){
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);

    }
    public void onBindViewHolder(ViewHolder holder, int position){
        ToDoModel item = toDoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
    }

    public int getItemCount(){
        return toDoList.size();
    }

    private boolean toBoolean (int n){
        return n != 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.toDoCheckBox);
        }
    }

}
