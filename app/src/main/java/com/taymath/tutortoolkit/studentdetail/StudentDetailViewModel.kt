package com.taymath.tutortoolkit.studentdetail

import androidx.lifecycle.*
import com.taymath.tutortoolkit.studentdatabase.Student
import com.taymath.tutortoolkit.studentdatabase.StudentDatabaseDao
import kotlinx.coroutines.*

class StudentDetailViewModel(
    val studentId: Long,
    dataSource: StudentDatabaseDao
): ViewModel() {

    /**
     * Hold a reference to StudentDatabase via its StudentDatabaseDao.
     */
    val database = dataSource

    // Initialize mediatorLiveData with student datatype
    private val student = MediatorLiveData<Student>()
    fun getStudent() = student

    // Grab grades from grades_table with StudentId
    val grades = database.getGradesWithStudentId(studentId)

    // add student from database as source for student
    init {
        student.addSource(database.getStudentWithId(studentId), student::setValue)
    }

    // Initialize viewModelJob
    private val viewModelJob = Job()

    // Initialize uiScope
    private val uiScope =  CoroutineScope(Dispatchers.Main + viewModelJob)

    // Setup Livedata to signal when to navigate to AddGrade
    private val _navigateToAddGrade = MutableLiveData<Boolean?>()
    val navigateToAddGrade: LiveData<Boolean?>
        get() = _navigateToAddGrade

    // Setup Livedata to signal when to navigate to studentList
    private val _navigateToStudentList = MutableLiveData<Boolean>()
    val navigateToStudentList: LiveData<Boolean>
        get() = _navigateToStudentList

    // Setup Livedata to tell whethere the clear button should be visible
    // Based on the contents of our grades list
    val clearButtonVisible = Transformations.map(grades) {
        it?.isNotEmpty()
    }

    fun onNavigateToStudentList() {
        _navigateToStudentList.value = true
    }

    fun onNavigateToAddGrade() {
        _navigateToAddGrade.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigating() {
        _navigateToAddGrade.value = null
        _navigateToStudentList.value = null
    }

    fun onClear() {
        uiScope.launch {
            // Clear the database table.
            clearGrades(studentId)
        }
    }

    private suspend fun clearGrades(studentId: Long) {
        withContext(Dispatchers.IO) {
            database.deleteGradeTable(studentId)
        }
    }

    private suspend fun deleteStudent(studentId: Long) {
        withContext(Dispatchers.IO) {
            database.deleteGradeTable(studentId)
            database.deleteStudent(studentId)
        }
    }

    fun onDeleteStudent() {
        uiScope.launch {
            deleteStudent(studentId)
        }
        _navigateToStudentList.value = true
    }

}