package com.taymath.tutortoolkit.studentlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.taymath.tutortoolkit.studentdatabase.Student
import com.taymath.tutortoolkit.studentdatabase.StudentDatabaseDao
import kotlinx.coroutines.*

class StudentListViewModel(
    val database: StudentDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    /** Coroutine setup variables */

    /**
     * viewModelJob allows us to cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = Job()

    // Add variable to navigation signaller
    private val _navigateToAddStudent = MutableLiveData<Boolean?>()
    val navigateToAddStudent: LiveData<Boolean?>
        get() = _navigateToAddStudent

    private val _navigateToStudentDetail = MutableLiveData<Long?>()
    val navigateToStudentDetail: LiveData<Long?>
        get() = _navigateToStudentDetail

    val students = database.getAllStudents()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    // set navigate to student to be true when clicked
    fun onNavToAddStudent() {
        _navigateToAddStudent.value = true
    }

    // Reset nav value after navigation
    fun onAddStudentNavigated() {
        _navigateToAddStudent.value = null
    }

    fun onNavToStudentDetail(studentId: Long) {
        _navigateToStudentDetail.value = studentId
    }

    fun onStudentDetailNavigated() {
        _navigateToStudentDetail.value = null
    }

}