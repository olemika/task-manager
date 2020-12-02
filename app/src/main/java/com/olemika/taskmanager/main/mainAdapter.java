package com.olemika.taskmanager.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.olemika.taskmanager.R;

import java.util.List;


public class mainAdapter extends RecyclerView.Adapter<mainAdapter.mainViewHolder> {
    private static final String TAG = "mainAdapter";

    AppDatabase db = App.getInstance().getDatabase();
    public GroupDao gDao =  db.groupDao();
    TaskDao tDao = db.taskDao();

    private Context mContext;
    private List<Group> mGroups;

    public mainAdapter(Context mContext, List<Group> mGroups) {
        this.mContext = mContext;
        this.mGroups = mGroups;
    }

    public static class  mainViewHolder extends RecyclerView.ViewHolder {
        TextView mainText;
        TextView progressText;
        LinearLayout parentLayout;
        ImageButton dltCategory;
        ProgressBar progressBar;

        public mainViewHolder(View itemView) {
            super(itemView);
            this.mainText = itemView.findViewById(R.id.main_text);
            this.parentLayout = itemView.findViewById(R.id.parent_layout);
            this.dltCategory = itemView.findViewById(R.id.btn_delete_category);
            this.progressBar = itemView.findViewById(R.id.progressBar);
            this.progressText = itemView.findViewById(R.id.text_progress);
        }
    }

    @Override
    public mainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list, parent, false);
        mainViewHolder holder = new mainViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(mainViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.mainText.setText(mGroups.get(position).getName());
        holder.dltCategory.setOnClickListener(v -> {
            gDao.delete(mGroups.get(position));
            Intent intent = new Intent(mContext, MainActivity.class);
            mContext.startActivity(intent);
        });
        holder.parentLayout.setOnClickListener(v -> {
            Log.d(TAG, "onClick: clicked on " + mGroups.get(position).getName());
            Intent intent = new Intent(mContext, listActivity.class);
            intent.putExtra("GroupId", (mGroups.get(position)).getId());
            mContext.startActivity(intent);

        });
        holder.progressBar.setMax(tDao.getCountByGroupId(mGroups.get(position).getId()));
        holder.progressBar.setProgress(tDao.getCountCheckedByGroupId(mGroups.get(position).getId()));
        holder.progressText.setText(tDao.getCountCheckedByGroupId(mGroups.get(position).getId()) + "/" + tDao.getCountByGroupId(mGroups.get(position).getId()));
    }



    @Override
    public int getItemCount() {
        return mGroups.size();
    }
}
