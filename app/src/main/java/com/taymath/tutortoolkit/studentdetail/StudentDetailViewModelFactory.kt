package com.taymath.tutortoolkit.studentdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taymath.tutortoolkit.studentdatabase.Student
import com.taymath.tutortoolkit.studentdatabase.StudentDatabaseDao

class StudentDetailViewModelFactory(
    private val dataSource: StudentDatabaseDao,
    private val studentId: Long
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentDetailViewModel::class.java)) {
            return StudentDetailViewModel(studentId, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}