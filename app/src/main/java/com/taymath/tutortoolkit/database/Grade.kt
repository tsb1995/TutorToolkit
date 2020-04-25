package com.taymath.tutortoolkit.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grade_table")
data class Grade  (

    @PrimaryKey(autoGenerate = true)
    var gradeId: Long = 0L,

    @ColumnInfo(name="grade_string")
    var gradeString: String = "BLANK Grade",

    @ColumnInfo(name = "student_name_string")
    var studentNameString: String = "CHECK_GRADE.KT",

    @ColumnInfo(name = "grade_float")
    var gradeFloat: Float = 0F,

    @ColumnInfo(name = "time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "note_string")
    var noteString: String = "CHECK_GRADE.KT",

    @ColumnInfo(name= "student_id_long")
    var studentIdLong: Long = 0L
)