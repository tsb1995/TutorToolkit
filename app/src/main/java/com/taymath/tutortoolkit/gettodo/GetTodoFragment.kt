/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taymath.tutortoolkit.gettodo

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
import com.taymath.tutortoolkit.database.StudentDatabase
import com.taymath.tutortoolkit.databinding.FragmentGetTodoBinding

/**
 * Fragment that displays a list of clickable icons,
 * each representing a sleep quality rating.
 * Once the user taps an icon, the quality is set in the current sleepNight
 * and the database is updated.
 */
class GetTodoFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_get_todo.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentGetTodoBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_get_todo, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = StudentDatabase.getInstance(application).studentDatabaseDao

        val viewModelFactory = GetTodoViewModelFactory(dataSource)

        val getTodoViewModel =
                ViewModelProviders.of(
                        this, viewModelFactory).get(GetTodoViewModel::class.java)

        binding.getTodoViewModel = getTodoViewModel

        getTodoViewModel.navigateToTodoList.observe(viewLifecycleOwner,  Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                        GetTodoFragmentDirections.actionGetTodoFragmentToTodoListFragment())
                getTodoViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}
