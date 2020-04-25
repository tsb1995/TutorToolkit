package com.taymath.tutortoolkit.studentdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.taymath.tutortoolkit.databinding.ListItemGradeBinding
import com.taymath.tutortoolkit.database.Grade

class GradeAdapter : ListAdapter<Grade,
        GradeAdapter.ViewHolder>(GradeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(val binding: ListItemGradeBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Grade) {
            binding.grade = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemGradeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class GradeDiffCallback : DiffUtil.ItemCallback<Grade>() {
        override fun areItemsTheSame(oldItem: Grade, newItem: Grade): Boolean {
            return oldItem.gradeId == newItem.gradeId
        }

        override fun areContentsTheSame(oldItem: Grade, newItem: Grade): Boolean {
            return oldItem == newItem
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */


class GradeDiffCallback : DiffUtil.ItemCallback<Grade>() {
    override fun areItemsTheSame(oldItem: Grade, newItem: Grade): Boolean {
        return oldItem.gradeId == newItem.gradeId
    }

    override fun areContentsTheSame(oldItem: Grade, newItem: Grade): Boolean {
        return oldItem == newItem
    }
}