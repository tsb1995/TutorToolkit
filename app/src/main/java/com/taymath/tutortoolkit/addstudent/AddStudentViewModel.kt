package com.taymath.tutortoolkit.addstudent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taymath.tutortoolkit.studentdatabase.Student
import com.taymath.tutortoolkit.studentdatabase.StudentDatabaseDao
import kotlinx.coroutines.*

class AddStudentViewModel(
    val database: StudentDatabaseDao
) : ViewModel() {

    // Initialize viewModelJob
    private val viewModelJob = Job()

    // Initialize uiScope
    private val uiScope =  CoroutineScope(Dispatchers.Main + viewModelJob)

    // Setup Livdata to signal when to navigate to studentList
    private val _navigateToStudentList =  MutableLiveData<Boolean?>()
    val navigateToStudentList: LiveData<Boolean?>
        get() = _navigateToStudentList

    // Initialize Student and add to student_table
    fun onAddStudent(studentNameString : String, subjectString: String,
    gradeLevelString: String, addressString: String, emailString: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val newStudent = Student()
                newStudent.studentNameString = studentNameString
                newStudent.subjectString = subjectString
                newStudent.emailString = emailString
                newStudent.gradeLevelString = gradeLevelString
                newStudent.addressString = addressString
                insert(newStudent)
            }
        }
        _navigateToStudentList.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigating() {
        _navigateToStudentList.value = null
    }

    private suspend fun insert(student: Student) {
        withContext(Dispatchers.IO) {
            database.insertStudent(student)
        }
    }

//    fun onSetSleepQuality(quality: Int) {
//        uiScope.launch {
//            withContext(Dispatchers.IO) {
//                val tonight = database.get(sleepNightKey) ?: return@withContext
//                tonight.sleepQuality = quality
//                database.update(tonight)
//            }
//            _navigateToSleepTracker.value = true
//        }
//    }
}