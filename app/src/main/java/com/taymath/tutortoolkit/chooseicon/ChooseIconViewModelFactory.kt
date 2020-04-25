package com.taymath.tutortoolkit.chooseicon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taymath.tutortoolkit.database.StudentDatabaseDao

/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the key for the night and the SleepDatabaseDao to the ViewModel.
 */
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