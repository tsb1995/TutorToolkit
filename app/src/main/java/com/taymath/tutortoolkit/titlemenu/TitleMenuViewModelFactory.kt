package com.taymath.tutortoolkit.titlemenu

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taymath.tutortoolkit.database.StudentDatabaseDao

class TitleMenuViewModelFactory(
    private val dataSource: StudentDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TitleMenuViewModel::class.java)) {
            return TitleMenuViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}