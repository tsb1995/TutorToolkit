package com.taymath.tutortoolkit.studentlist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.taymath.tutortoolkit.R
import com.taymath.tutortoolkit.studentdatabase.Student

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

@BindingAdapter("studentImage")
fun ImageView.setStudentImage(item: Student?) {
    item?.let {
        setImageResource(when (item.iconNumber) {
                1 -> R.drawable.icon_1
                2 -> R.drawable.icon_2
                3 -> R.drawable.icon_3
                4 -> R.drawable.icon_4
                5 -> R.drawable.icon_5
                6 -> R.drawable.icon_6
                7 -> R.drawable.icon_7
                8 -> R.drawable.icon_8
                else -> R.drawable.icon_1
        })
    }
}
