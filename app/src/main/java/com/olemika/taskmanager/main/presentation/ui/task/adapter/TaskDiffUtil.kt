package com.olemika.taskmanager.main.presentation.ui.task.adapter

import androidx.recyclerview.widget.DiffUtil
import com.olemika.taskmanager.main.data.db.entity.Task

class TaskDiffUtil(private val oldList: List<Task>, private val newList: List<Task>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTask = oldList[oldItemPosition]
        val newTask = newList[newItemPosition]

        return oldTask.id == newTask.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTask = oldList[oldItemPosition]
        val newTask = newList[newItemPosition]

        return oldTask.id == newTask.id && oldTask.isDone == newTask.isDone
    }
}