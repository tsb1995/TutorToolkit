package com.taymath.tutortoolkit.todolist

import android.app.Application
import androidx.lifecycle.*
import com.taymath.tutortoolkit.database.Student
import com.taymath.tutortoolkit.database.StudentDatabaseDao
import com.taymath.tutortoolkit.database.Todo
import kotlinx.coroutines.*

class TodoListViewModel(
    val database: StudentDatabaseDao
) : ViewModel() {

    // Grab grades from grades_table with StudentId
    val todos = database.getAllTodos()


    private val viewModelJob = Job()

    // Initialize uiScope
    private val uiScope =  CoroutineScope(Dispatchers.Main + viewModelJob)

    // Add variable to navigation signaller
    private val _navigateToAddTodo = MutableLiveData<Boolean?>()
    val navigateToAddTodo: LiveData<Boolean?>
        get() = _navigateToAddTodo

    private val _deleteTodo = MutableLiveData<Boolean?>()
    val deleteTodo: LiveData<Boolean?>
        get() = _deleteTodo

    val students = database.getAllStudents()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onDeleteTodo(todoId: Long) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.deleteTodoById(todoId)
            }
        }
    }

    // set navigate to student to be true when clicked
    fun onNavToAddStudent() {
        _navigateToAddTodo.value = true
    }

    // Reset nav value after navigation
    fun doneNavigating() {
        _navigateToAddTodo.value = null
    }

}