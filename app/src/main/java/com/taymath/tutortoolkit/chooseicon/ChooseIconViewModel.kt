package com.taymath.tutortoolkit.chooseicon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taymath.tutortoolkit.studentdatabase.StudentDatabaseDao
import kotlinx.coroutines.*

/**
 * ViewModel for SleepQualityFragment.
 *
 * @param sleepNightKey The key of the current night we are working on.
 */
class ChooseIconViewModel(
    val database: StudentDatabaseDao) : ViewModel() {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToStudentList = MutableLiveData<Boolean?>()

    /**
     * When true immediately navigate back to the [SleepTrackerFragment]
     */
    val navigateToStudentList: LiveData<Boolean?>
        get() = _navigateToStudentList
    /**
     * Cancels all coroutines when the ViewModel is cleared, to cleanup any pending work.
     *
     * onCleared() gets called when the ViewModel is destroyed.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /**
     * Call this immediately after navigating to [StudentListFragment]
     */
    fun doneNavigating() {
        _navigateToStudentList.value = null
    }

    fun onChooseIcon(iconNumber: Int) {
        uiScope.launch {
            // IO is a thread pool for running operations that access the disk, such as
            // our Room database.
            withContext(Dispatchers.IO) {
                val currentStudent = database.getCurrentStudent() ?: return@withContext
                currentStudent.iconNumber = iconNumber
                database.updateStudent(currentStudent)
            }
        }
        // Setting this state variable to true will alert the observer and trigger navigation.
        _navigateToStudentList.value = true
    }
}