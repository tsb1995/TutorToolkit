package com.taymath.tutortoolkit.chooseicon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.taymath.tutortoolkit.R
import com.taymath.tutortoolkit.databinding.FragmentChooseIconBinding
import com.taymath.tutortoolkit.studentdatabase.StudentDatabase

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

//        val arguments = SleepQualityFragmentArgs.fromBundle(arguments!!)

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
//        chooseIconViewModel.navigateToSleepTracker.observe(this, Observer {
//            if (it == true) { // Observed state is true.
//                this.findNavController().navigate(
//                    SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
//                // Reset state to make sure we only navigate once, even if the device
//                // has a configuration change.
//                sleepQualityViewModel.doneNavigating()
//            }
//        })

        return binding.root
    }
}