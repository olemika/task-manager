package com.olemika.taskmanager.main.presentation.ui.main.adapter;

import android.content.Context;
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
import com.olemika.taskmanager.main.App;
import com.olemika.taskmanager.main.data.db.AppDatabase;
import com.olemika.taskmanager.main.data.db.entity.Group;
import com.olemika.taskmanager.main.data.db.dao.TaskDao;
import com.olemika.taskmanager.main.presentation.ui.utils.AdapterClickListener;

import java.util.List;




public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private static final String TAG = "mainAdapter";

    AppDatabase db = App.getInstance().getDatabase();
    TaskDao tDao = db.taskDao();
    private AdapterClickListener<Group> clickListener;
    private Context mContext;
    private List<Group> mGroups;

    public MainAdapter(Context mContext, List<Group> mGroups, AdapterClickListener<Group> clickListener) {
        this.mContext = mContext;
        this.mGroups = mGroups;
        this.clickListener = clickListener;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView mainText;
        TextView progressText;
        LinearLayout parentLayout;
        ImageButton dltCategory;
        ProgressBar progressBar;

        public MainViewHolder(View itemView) {
            super(itemView);
            this.mainText = itemView.findViewById(R.id.main_text);
            this.parentLayout = itemView.findViewById(R.id.parent_layout);
            this.dltCategory = itemView.findViewById(R.id.btn_delete_category);
            this.progressBar = itemView.findViewById(R.id.progressBar);
            this.progressText = itemView.findViewById(R.id.text_progress);
        }
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list, parent, false);
//        MainViewHolder holder = new MainViewHolder(view);
        return new MainViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        //init item
        Group group = (mGroups.get(position));
        holder.mainText.setText(group.getName());
        holder.dltCategory.setOnClickListener(v -> {
            clickListener.onDelete(group);
//            gDao.delete(group);
//            Intent intent = new Intent(mContext, MainActivity.class);
//            mContext.startActivity(intent);
        });
        holder.itemView.setOnClickListener(v -> {
            Log.d(TAG, "onClick: clicked on " + mGroups.get(position).getName());
            clickListener.onClick(group);
//            Intent intent = new Intent(mContext, listActivity.class);
//            intent.putExtra("GroupId", (mGroups.get(position)).getId());
//            mContext.startActivity(intent);

        });
        holder.progressBar.setMax(tDao.getCountByGroupId(mGroups.get(position).getId()));
        holder.progressBar.setProgress(tDao.getCountCheckedByGroupId(mGroups.get(position).getId()));
        holder.progressText.setText(tDao.getCountCheckedByGroupId(mGroups.get(position).getId()) + "/" + tDao.getCountByGroupId(mGroups.get(position).getId()));
    }

    public void addGroup(Group group){
        mGroups.add(0, group);
        notifyItemInserted(0);
    }

    public void deleteGroup(Group group){
        mGroups.remove(group);
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return mGroups.size();
    }
}
