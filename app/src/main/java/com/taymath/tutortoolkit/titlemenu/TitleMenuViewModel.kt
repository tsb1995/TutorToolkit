package com.taymath.tutortoolkit.titlemenu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.taymath.tutortoolkit.database.StudentDatabaseDao

class TitleMenuViewModel(
    val database: StudentDatabaseDao,
    application: Application): AndroidViewModel(application) {

    private val _navigateToStudentList = MutableLiveData<Boolean?>()
    val navigateToStudentList: LiveData<Boolean?>
        get() = _navigateToStudentList

    private val _navigateToTodoList = MutableLiveData<Boolean?>()
    val navigateToTodoList: LiveData<Boolean?>
        get() = _navigateToTodoList

    fun onNavigateToStudentList() {
        _navigateToStudentList.value = true
    }

    fun onNavigateToTodoList(){
        _navigateToTodoList.value = true
    }

    fun onNavigated() {
        _navigateToStudentList.value = false
        _navigateToTodoList.value = false
    }

}