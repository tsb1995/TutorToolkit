package com.taymath.tutortoolkit.todolist

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.taymath.tutortoolkit.database.Todo

@BindingAdapter("todoText")
fun TextView.setGradeLevelText(item: Todo?){
    item?.let {
        text =item.todoString
    }
}

