package com.taymath.tutortoolkit.titlemenu

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
import com.taymath.tutortoolkit.databinding.FragmentTitleMenuBinding
import com.taymath.tutortoolkit.database.StudentDatabase

class TitleMenuFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentTitleMenuBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title_menu, container, false)

        // Get reference to application
        val application = requireNotNull(this.activity).application

        // Get a reference to the DAO
        val dataSource = StudentDatabase.getInstance(application).studentDatabaseDao

        // Create instance of viewModelFactory using DAO and application
        val viewModelFactory = TitleMenuViewModelFactory(dataSource, application)

        // Get reference to viewModel
        val titleMenuViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(TitleMenuViewModel::class.java)

        // Add ViewModel to our binding
        binding.titleMenuViewModel = titleMenuViewModel

        binding.setLifecycleOwner(this)

        // Add an Observer to the state variable for Navigating when Add Student button is clicked.
        titleMenuViewModel.navigateToStudentList.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    TitleMenuFragmentDirections.actionTitleMenuFragmentToStudentListFragment())
                titleMenuViewModel.onNavigated()
            }
        })

        // Add an Observer to the state variable for Navigating when Add Student button is clicked.
        titleMenuViewModel.navigateToTodoList.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    TitleMenuFragmentDirections.actionTitleMenuFragmentToTodoListFragment())
                titleMenuViewModel.onNavigated()
            }
        })

        return binding.root
    }

}