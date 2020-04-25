package com.taymath.tutortoolkit.studentlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taymath.tutortoolkit.database.StudentDatabaseDao
import kotlinx.coroutines.*

class StudentListViewModel(
    val database: StudentDatabaseDao
) : ViewModel() {

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