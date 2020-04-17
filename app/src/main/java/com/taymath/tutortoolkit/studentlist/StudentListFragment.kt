package com.taymath.tutortoolkit.studentlist

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
import com.taymath.tutortoolkit.databinding.FragmentStudentListBinding
import com.taymath.tutortoolkit.studentdatabase.StudentDatabase
import kotlinx.android.synthetic.main.fragment_add_student.*

/**
 * A fragment with buttons to record start and end times for sleep, which are saved in
 * a database. Cumulative data is displayed in a simple scrollable TextView.
 * (Because we have not learned about RecyclerView yet.)
 */
class StudentListFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_get_todo.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentStudentListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_student_list, container, false)

        // Get reference to application
        val application = requireNotNull(this.activity).application

        // Get a reference to the DAO
        val dataSource = StudentDatabase.getInstance(application).studentDatabaseDao

        // Create instance of viewModelFactory using DAO and application
        val viewModelFactory = StudentListViewModelFactory(dataSource, application)

        // Get reference to viewModel
        val studentListViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(StudentListViewModel::class.java)

        // Add ViewModel to our binding
        binding.studentListViewModel = studentListViewModel

        binding.setLifecycleOwner(this)

        // Add an Observer to the state variable for Navigating when Add Student button is clicked.
        studentListViewModel.navigateToAddStudent.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    StudentListFragmentDirections.actionStudentListFragmentToAddStudentFragment())
                studentListViewModel.onAddStudentNavigated()
            }
        })

        // Add an Observer to the state variable for Navigating when student icon is clicked.
        studentListViewModel.navigateToStudentDetail.observe(viewLifecycleOwner, Observer {student ->
            student?.let {
                this.findNavController().navigate(StudentListFragmentDirections
                    .actionStudentListFragmentToStudentDetailFragment(student))
                studentListViewModel.onStudentDetailNavigated()
            }
        })

        // Initialize adapter and add to studentList
        val adapter = StudentAdapter(StudentListener {
            studentId -> studentListViewModel.onNavToStudentDetail(studentId)
        })
        binding.studentList.adapter = adapter

        // If we have a list of students, send it to the adapter
        studentListViewModel.students.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}
