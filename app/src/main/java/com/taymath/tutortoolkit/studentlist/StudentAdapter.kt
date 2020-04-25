package com.taymath.tutortoolkit.studentlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taymath.tutortoolkit.databinding.ListItemStudentBinding
import com.taymath.tutortoolkit.database.Student

class StudentAdapter(val clickListener: StudentListener) : ListAdapter<Student, StudentAdapter.ViewHolder>(StudentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position)!!)
    }


    class ViewHolder private constructor(val binding: ListItemStudentBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: StudentListener, item: Student) {
            binding.student = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemStudentBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    class StudentDiffCallback : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.studentId == newItem.studentId
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }

    }
}

class StudentListener(val clickListener: (studentId: Long) -> Unit) {
    fun onClick(student: Student) = clickListener(student.studentId)
}