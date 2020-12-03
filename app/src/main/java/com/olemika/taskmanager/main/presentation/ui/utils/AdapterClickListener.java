package com.olemika.taskmanager.main.presentation.ui.utils;

public interface AdapterClickListener<T> {
    void onClick(T item);
    void onDelete(T item);
}
