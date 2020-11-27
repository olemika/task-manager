package com.olemika.taskmanager.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.olemika.taskmanager.R;

import java.util.ArrayList;
import java.util.List;


public class mainAdapter extends RecyclerView.Adapter<mainAdapter.mainViewHolder> {
    private static final String TAG = "mainAdapter";

    AppDatabase db = App.getInstance().getDatabase();
    public GroupDao gDao =  db.groupDao();

    private Context mContext;
    private ArrayList<Integer> mImages = new ArrayList<>();
    private List<Group> mGroups = new ArrayList<>();

    public mainAdapter(Context mContext, ArrayList<Integer> mImages, List<Group> mGroups) {
        this.mContext = mContext;
        this.mImages = mImages;
        this.mGroups = mGroups;
    }

    public static class  mainViewHolder extends RecyclerView.ViewHolder {
        TextView mainText;
        ImageView mainImage;
        LinearLayout parentLayout;
        ImageButton dltCategory;

        public mainViewHolder(View itemView) {
            super(itemView);
            this.mainText = itemView.findViewById(R.id.main_text);
            this.mainImage = itemView.findViewById(R.id.main_image);
            this.parentLayout = itemView.findViewById(R.id.parent_layout);
            this.dltCategory = itemView.findViewById(R.id.btn_delete_category);
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
        holder.mainImage.setImageResource(mImages.get(position));
        holder.mainText.setText(mGroups.get(position).getName());
        holder.dltCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gDao.delete(mGroups.get(position));
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
            }
        });
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + mGroups.get(position).getName());
                Intent intent = new Intent(mContext, listActivity.class);
                intent.putExtra("GroupId", (mGroups.get(position)).getId());
                mContext.startActivity(intent);

            }
        })

    ;}



    @Override
    public int getItemCount() {
        return mGroups.size();
    }
}
