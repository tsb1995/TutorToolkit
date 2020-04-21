package com.taymath.tutortoolkit.chooseicon

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
import com.taymath.tutortoolkit.databinding.FragmentChooseIconBinding
import com.taymath.tutortoolkit.studentdatabase.StudentDatabase

/**
 * Fragment that displays a list of clickable icons,
 * each representing a sleep quality rating.
 * Once the user taps an icon, the quality is set in the current sleepNight
 * and the database is updated.
 */
class ChooseIconFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_sleep_quality.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentChooseIconBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_choose_icon, container, false)

        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = StudentDatabase.getInstance(application).studentDatabaseDao
        val viewModelFactory = ChooseIconViewModelFactory(dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val chooseIconViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(ChooseIconViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.chooseIconViewModel = chooseIconViewModel

        // Add an Observer to the state variable for Navigating when a Quality icon is tapped.
        chooseIconViewModel.navigateToStudentList.observe(viewLifecycleOwner, Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    ChooseIconFragmentDirections.actionChooseIconFragmentToStudentListFragment())
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                chooseIconViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}
