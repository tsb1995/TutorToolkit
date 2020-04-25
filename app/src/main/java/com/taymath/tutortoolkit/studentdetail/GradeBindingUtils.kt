package com.taymath.tutortoolkit.studentdetail

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.taymath.tutortoolkit.database.Grade

@BindingAdapter("gradeFloat")
fun TextView.setGradeFloat(item: Grade?){
    item?.let {
        text = item.gradeFloat.toString()
    }
}

@BindingAdapter("gradeString")
fun TextView.setSubjectText(item: Grade?){
    item?.let {
        text =item.gradeString
    }
}

@BindingAdapter("noteString")
fun TextView.setStudentNameText(item: Grade?){
    item?.let {
        text =item.noteString
    }
}
