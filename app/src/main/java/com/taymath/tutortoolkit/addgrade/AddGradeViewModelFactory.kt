package com.taymath.tutortoolkit.addgrade

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taymath.tutortoolkit.database.StudentDatabaseDao

class AddGradeViewModelFactory(
    private val student_id: Long,
    private val dataSource: StudentDatabaseDao
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddGradeViewModel::class.java)) {
            return AddGradeViewModel(student_id, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
