package com.olemika.taskmanager.main.presentation.ui.task.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.olemika.taskmanager.R;
import com.olemika.taskmanager.main.App;
import com.olemika.taskmanager.main.data.db.AppDatabase;
import com.olemika.taskmanager.main.data.db.entity.Task;
import com.olemika.taskmanager.main.data.db.dao.TaskDao;
import com.olemika.taskmanager.main.presentation.ui.utils.AdapterClickListener;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.taskViewHolder>{
    private static final String TAG = "taskAdapter";

    private Context mContext;
    public List<Task> mTask;
    private AdapterClickListener<Task> clickListener;

    AppDatabase db = App.getInstance().getDatabase();
    public TaskDao tDao =  db.taskDao();


    public TaskAdapter(Context mContext, List<Task> mTask, AdapterClickListener<Task> clickListener) {
        this.mContext = mContext;
        this.mTask = mTask;
        this.clickListener = clickListener;
    }

    public static class taskViewHolder extends RecyclerView.ViewHolder {
        CheckBox taskText;
        ImageButton dltButton;
        LinearLayout parentLayout;

        public taskViewHolder(View itemView) {
            super(itemView);
            this.taskText = itemView.findViewById(R.id.checkBox);
            this.parentLayout = itemView.findViewById(R.id.task_parent);
            this.dltButton = itemView.findViewById(R.id.task_delete_button);
        }
    }

    @Override
    public taskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list, parent, false);
        taskViewHolder holder = new taskViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(taskViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.taskText.setText(mTask.get(position).getTaskName());
        holder.taskText.setChecked(mTask.get(position).getDone());
        Log.d(TAG, "onBindViewHolder: set checked " + mTask.get(position).getDone());
        holder.taskText.setOnClickListener(v -> {
            clickListener.onClick(mTask.get(position));
//            if (!mTask.get(position).getDone()) {
//                mTask.get(position).setDone(true);
//            } else {
//                mTask.get(position).setDone(false);
//            }
//            tDao.update(mTask.get(position));
        });
        holder.dltButton.setOnClickListener(v -> {
            clickListener.onDelete(mTask.get(position));
//            tDao.delete(mTask.get(position));
//            Intent intent = new Intent(mContext, listActivity.class);
//            intent.putExtra("GroupId", (mTask.get(position)).getGroupId());
//            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mTask.size();
    }

    public void update(List<Task> newList){
        mTask = newList;
//        notifyDataSetChanged();

    }
}


