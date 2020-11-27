package com.olemika.taskmanager.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.olemika.taskmanager.R;

import java.util.List;

public class taskAdapter extends RecyclerView.Adapter<taskAdapter.taskViewHolder>{
    private static final String TAG = "taskAdapter";

    private Context mContext;
    private List<Task> mTask;


    AppDatabase db = App.getInstance().getDatabase();
    public TaskDao tDao =  db.taskDao();

    public taskAdapter(Context mContext, List<Task> mTask) {
        this.mContext = mContext;
        this.mTask = mTask;
    }

    public static class taskViewHolder extends RecyclerView.ViewHolder {
        TextView taskText;
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
        holder.taskText.setText((mTask.get(position)).getTaskName());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + mTask.get(position).getTaskName());
                Toast.makeText(mContext, mTask.get(position).getTaskName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.dltButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tDao.delete(mTask.get(position));
                Intent intent = new Intent(mContext, listActivity.class);
                intent.putExtra("GroupId", (mTask.get(position)).getGroupId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTask.size();
    }
}


