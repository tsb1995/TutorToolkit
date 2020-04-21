package com.taymath.tutortoolkit.addstudent

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
import com.taymath.tutortoolkit.databinding.FragmentAddStudentBindingImpl
import com.taymath.tutortoolkit.studentdatabase.StudentDatabase

class AddStudentFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_add_student.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentAddStudentBindingImpl = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_student, container, false)

        // Get reference to application
        val application = requireNotNull(this.activity).application

        // Get a reference to the DAO
        val dataSource = StudentDatabase.getInstance(application).studentDatabaseDao

        // Create instance of viewModelFactory using DAO and application
        val viewModelFactory = AddStudentViewModelFactory(dataSource)

        // Get reference to viewModel
        val addStudentViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(AddStudentViewModel::class.java)

        // Add our viewModel to binding
        binding.addStudentViewModel = addStudentViewModel

        binding.setLifecycleOwner(this)

//        // Add an Observer to the state variable for Navigating when student icon is clicked.
//        addStudentViewModel.navigateToChooseIcon.observe(viewLifecycleOwner, Observer {student ->
//            student?.let {
//                this.findNavController().navigate(AddStudentFragmentDirections
//                    .actionAddStudentFragmentToChooseIconFragment(student.studentId))
//                addStudentViewModel.doneNavigating()
//            }
//        })

        // Add an Observer to the state variable for Navigating when a Quality icon is tapped.
        addStudentViewModel.navigateToChooseIcon.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    AddStudentFragmentDirections.actionAddStudentFragmentToChooseIconFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                addStudentViewModel.doneNavigating()
            }
        })


        return binding.root
    }
}