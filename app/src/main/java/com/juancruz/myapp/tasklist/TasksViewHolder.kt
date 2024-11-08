package com.juancruz.myapp.tasklist

import android.content.res.ColorStateList
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.juancruz.myapp.R

class TasksViewHolder (view: View): RecyclerView.ViewHolder(view){
    private val tvTasksName: TextView = view.findViewById(R.id.tvTaskName)
    private val checkBox: CheckBox = view.findViewById(R.id.cbTaskselect)

    fun render (tasks: Tasks){

        if (tasks.isSelect){
            tvTasksName.paintFlags = tvTasksName.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvTasksName.paintFlags = tvTasksName.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        tvTasksName.text = tasks.name
        checkBox.isChecked = tasks.isSelect

        val color = when(tasks.category){
            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Other -> R.color.todo_other_category
            TaskCategory.Personal -> R.color.todo_personal_category
        }

        checkBox.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(checkBox.context, color)
        )
    }
}