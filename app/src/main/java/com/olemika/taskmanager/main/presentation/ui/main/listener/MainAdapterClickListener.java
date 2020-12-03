package com.olemika.taskmanager.main.presentation.ui.main.listener;

import com.olemika.taskmanager.main.data.db.entity.Group;

public interface MainAdapterClickListener {
    public void onClick(Group group);

    public void onDelete(Group group);
}