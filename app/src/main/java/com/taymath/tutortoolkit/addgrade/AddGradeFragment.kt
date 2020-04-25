package com.taymath.tutortoolkit.addgrade

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
import com.taymath.tutortoolkit.databinding.FragmentAddGradeBindingImpl
import com.taymath.tutortoolkit.database.StudentDatabase

class AddGradeFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_add_grade.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentAddGradeBindingImpl = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_grade, container, false)

        // Get reference to application
        val application = requireNotNull(this.activity).application

        // Get a reference to the DAO
        val dataSource = StudentDatabase.getInstance(application).studentDatabaseDao

        // Grab our arguments from previous fragment
        val arguments = AddGradeFragmentArgs.fromBundle(arguments!!)

        // Create instance of viewModelFactory using DAO and application
        val viewModelFactory = AddGradeViewModelFactory(arguments.studentId, dataSource)

        // Get reference to viewModel
        val addGradeViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(AddGradeViewModel::class.java)

        // Add view model to our binding
        binding.addGradeViewModel = addGradeViewModel

        binding.setLifecycleOwner(this)

        // Listen for when we navigate to student list
        addGradeViewModel.navigateToStudentList.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    AddGradeFragmentDirections.actionAddGradeFragmentToStudentListFragment())
                addGradeViewModel.doneNavigating()
            }
        })
        return binding.root
    }
}