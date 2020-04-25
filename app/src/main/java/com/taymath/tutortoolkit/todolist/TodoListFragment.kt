package com.taymath.tutortoolkit.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.taymath.tutortoolkit.R
import com.taymath.tutortoolkit.database.StudentDatabase
import com.taymath.tutortoolkit.databinding.FragmentTodoListBinding

class TodoListFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_get_todo.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentTodoListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_todo_list, container, false)

        // Get reference to application
        val application = requireNotNull(this.activity).application

        // Get a reference to the DAO
        val dataSource = StudentDatabase.getInstance(application).studentDatabaseDao

        // Create instance of viewModelFactory using DAO
        val viewModelFactory = TodoListViewModelFactory(dataSource)

        // Get reference to viewModel
        val todoListViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(TodoListViewModel::class.java)

        binding.todoListViewModel = todoListViewModel

        binding.setLifecycleOwner(this)

        todoListViewModel.navigateToAddTodo.observe(viewLifecycleOwner,  Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    TodoListFragmentDirections.actionTodoListFragmentToGetTodoFragment())
                todoListViewModel.doneNavigating()
            }
        })

        // Initialize adapter and add to studentList
        val adapter = TodoAdapter(TodoAdapter.TodoListener {
                todoId -> todoListViewModel.onDeleteTodo(todoId)
        })
        binding.todoList.adapter = adapter

        // If we have a list of grades, send it to the adapter
        todoListViewModel.todos.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}