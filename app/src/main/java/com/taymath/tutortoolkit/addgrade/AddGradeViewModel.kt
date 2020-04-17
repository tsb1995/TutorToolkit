package com.taymath.tutortoolkit.addgrade

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taymath.tutortoolkit.studentdatabase.Grade
import com.taymath.tutortoolkit.studentdatabase.Student
import com.taymath.tutortoolkit.studentdatabase.StudentDatabaseDao
import kotlinx.coroutines.*

class AddGradeViewModel(
    var student_id: Long,
    val database: StudentDatabaseDao
) : ViewModel() {

    // Initialize viewModelJob
    private val viewModelJob = Job()

    // Initialize uiScope
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // Setup Livdata to signal when to navigate to studentList
    private val _navigateToStudentList = MutableLiveData<Boolean?>()
    val navigateToStudentList: LiveData<Boolean?>
        get() = _navigateToStudentList

    val gradeFloatText = MutableLiveData<String>()

    // Initialize mediatorLiveData containing Student Class
    private val student = MediatorLiveData<Student>()

    fun getStudent() = student

    // Add Student from database to our mediatorLiveData
    init {
        student.addSource(database.getStudentWithId(student_id), student::setValue)
    }

//    fun validateForm(): Boolean {
//        return gradeFloatText.value!!.isNotEmpty()
//    }


    // Initialize Grade and add to grade_table
    fun onAddGrade(
        gradeString: String,
        gradeFloat: Float,
        noteString: String,
        studentNameString: String
    ) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val newGrade = Grade()
                newGrade.studentNameString = studentNameString
                newGrade.gradeString = gradeString
                newGrade.gradeFloat = gradeFloat
                newGrade.noteString = noteString
                newGrade.studentIdLong = student_id
                insert(newGrade)
            }
        }
        _navigateToStudentList.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigating() {
        _navigateToStudentList.value = false
    }

    private fun insert(grade: Grade) {
        database.insertGrade(grade)
    }
}