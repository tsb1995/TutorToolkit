/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taymath.tutortoolkit.gettodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taymath.tutortoolkit.database.StudentDatabaseDao
import com.taymath.tutortoolkit.database.Todo
import kotlinx.coroutines.*

class GetTodoViewModel(
        val database: StudentDatabaseDao) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope =  CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _navigateToTodoList =  MutableLiveData<Boolean?>()

    val navigateToTodoList: LiveData<Boolean?>
        get() = _navigateToTodoList

    fun doneNavigating() {
        _navigateToTodoList.value = null
    }

    private suspend fun insert(todo: Todo) {
        withContext(Dispatchers.IO) {
            database.insertTodo(todo)
        }
    }

    fun onEnterTodo(todoString: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val newTodo = Todo()
                newTodo.todoString = todoString
                insert(newTodo)
                }
            }
            _navigateToTodoList.value = true
        }
}
