package com.taymath.tutortoolkit.studentlist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.taymath.tutortoolkit.R
import com.taymath.tutortoolkit.studentdatabase.Student

@BindingAdapter("studentImage")
fun ImageView.setStudentImage(item: Student?){
    item?.let {
        setImageResource(R.drawable.ic_item_test)
    }
}

@BindingAdapter("subjectText")
fun TextView.setSubjectText(item: Student?){
    item?.let {
        text =item.subjectString
    }
}

@BindingAdapter("studentNameText")
fun TextView.setStudentNameText(item: Student?){
    item?.let {
        text =item.studentNameString
    }
}

@BindingAdapter("gradeLevelText")
fun TextView.setGradeLevelText(item: Student?){
    item?.let {
        text =item.gradeLevelString
    }
}
