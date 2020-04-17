package com.taymath.tutortoolkit.studentdetail

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
import com.taymath.tutortoolkit.databinding.FragmentStudentDetailBinding
import com.taymath.tutortoolkit.studentdatabase.StudentDatabase
import com.taymath.tutortoolkit.studentlist.StudentListFragmentDirections
import com.taymath.tutortoolkit.studentlist.StudentListener

class StudentDetailFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding:FragmentStudentDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_student_detail, container,false
        )

        val application = requireNotNull(this.activity).application
        val arguments = StudentDetailFragmentArgs.fromBundle(arguments!!)

        // Create an instance of the ViewModel Factory.
        val dataSource = StudentDatabase.getInstance(application).studentDatabaseDao
        val viewModelFactory = StudentDetailViewModelFactory(dataSource, arguments.studentId)

        // Get a reference to the ViewModel associated with this fragment.
        val studentDetailViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(StudentDetailViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.studentDetailViewModel = studentDetailViewModel

        binding.setLifecycleOwner(this)

        // Add an Observer to the state variable for Navigating when Add Grade button is clicked.
        studentDetailViewModel.navigateToAddGrade.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    StudentDetailFragmentDirections.actionStudentDetailFragmentToAddGradeFragment(arguments.studentId))
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                studentDetailViewModel.doneNavigating()
            }
        })

        // Add an Observer to the state variable for Navigating when Add Grade button is clicked.
        studentDetailViewModel.navigateToStudentList.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    StudentDetailFragmentDirections.actionStudentDetailFragmentToStudentListFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                studentDetailViewModel.doneNavigating()
            }
        })

        // Initialize adapter and add to gradeList
        val adapter = GradeAdapter()
        binding.gradeList.adapter = adapter

        // If we have a list of grades, send it to the adapter
        studentDetailViewModel.grades.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}