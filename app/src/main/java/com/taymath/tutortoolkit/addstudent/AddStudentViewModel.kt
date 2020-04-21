package com.taymath.tutortoolkit.addstudent

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taymath.tutortoolkit.R
import com.taymath.tutortoolkit.studentdatabase.Student
import com.taymath.tutortoolkit.studentdatabase.StudentDatabaseDao
import com.taymath.tutortoolkit.studentdetail.StudentDetailFragmentArgs
import kotlinx.coroutines.*


class AddStudentViewModel(
    val database: StudentDatabaseDao
) : ViewModel() {

    val newStudent = Student()

    // Initialize viewModelJob
    private val viewModelJob = Job()

    // Initialize uiScope
    private val uiScope =  CoroutineScope(Dispatchers.Main + viewModelJob)

    private var currentStudent = MutableLiveData<Student?>()

    // Setup Livdata to signal when to navigate to chooseIcon
    private val _navigateToChooseIcon = MutableLiveData<Boolean?>()
    val navigateToChooseIcon: LiveData<Boolean?>
        get() = _navigateToChooseIcon

    // Initialize Student and add to student_table
    fun onChooseIcon(studentNameString : String, subjectString: String,
    gradeLevelString: String, addressString: String, emailString: String) {
        newStudent.studentNameString = studentNameString
        newStudent.subjectString = subjectString
        newStudent.emailString = emailString
        newStudent.gradeLevelString = gradeLevelString
        newStudent.addressString = addressString

        uiScope.launch {
            withContext(Dispatchers.IO) {
                insert(newStudent)
            }
        }
        _navigateToChooseIcon.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigating() {
        _navigateToChooseIcon.value = null
    }

    private suspend fun insert(student: Student) {
        withContext(Dispatchers.IO) {
            database.insertStudent(student)
        }
    }

    private suspend fun getCurrentStudentFromDatabase(): Student? {
        return withContext(Dispatchers.IO) {
            var student = database.getCurrentStudent()
            student
        }
    }

    private fun initializeCurrentStudent() {
        uiScope.launch {
            currentStudent.value = getCurrentStudentFromDatabase()
        }
    }

}