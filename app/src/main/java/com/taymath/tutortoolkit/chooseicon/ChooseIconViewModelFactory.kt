package com.taymath.tutortoolkit.chooseicon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taymath.tutortoolkit.studentdatabase.StudentDatabaseDao

class ChooseIconViewModelFactory(
    private val dataSource: StudentDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChooseIconViewModel::class.java)) {
            return ChooseIconViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}