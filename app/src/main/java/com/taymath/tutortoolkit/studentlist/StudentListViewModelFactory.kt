package com.taymath.tutortoolkit.studentlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taymath.tutortoolkit.database.StudentDatabaseDao

class StudentListViewModelFactory(
    private val dataSource: StudentDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentListViewModel::class.java)) {
            return StudentListViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}