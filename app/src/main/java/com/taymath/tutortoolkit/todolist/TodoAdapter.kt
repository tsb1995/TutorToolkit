package com.taymath.tutortoolkit.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taymath.tutortoolkit.database.Todo
import com.taymath.tutortoolkit.databinding.ListItemTodoBinding

class TodoAdapter(val clickListener: TodoListener) : ListAdapter<Todo, TodoAdapter.ViewHolder>(
    TodoDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position)!!)
    }


    class ViewHolder private constructor(val binding: ListItemTodoBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: TodoListener,item: Todo) {
            binding.todo = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemTodoBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }

    }

    class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.todoId == newItem.todoId
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }

    }

    class TodoListener(val clickListener: (todoId: Long) -> Unit) {
        fun onClick(todo: Todo) = clickListener(todo.todoId)
    }
}