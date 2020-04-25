package com.taymath.tutortoolkit.studentlist

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.taymath.tutortoolkit.R
import com.taymath.tutortoolkit.database.Student

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
                1 -> R.drawable.face1
                2 -> R.drawable.face2
                3 -> R.drawable.face3
                4 -> R.drawable.face4
                5 -> R.drawable.face5
                6 -> R.drawable.face6
                7 -> R.drawable.face7
                8 -> R.drawable.face8
                9 -> R.drawable.face9
                10 -> R.drawable.face10
                11 -> R.drawable.face11
                12 -> R.drawable.face12
                13 -> R.drawable.face13
                14 -> R.drawable.face14
                15 -> R.drawable.face15
                else -> R.drawable.face1
        })
    }
}
