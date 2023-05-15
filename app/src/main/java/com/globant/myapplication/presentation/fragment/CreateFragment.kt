package com.globant.myapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.globant.myapplication.R
import com.globant.myapplication.presentation.screen.CreateReminderScreen
import com.globant.myapplication.presentation.screen.DetailReminderScreen
import com.globant.myapplication.presentation.viewmodel.CreateViewModel
import com.globant.myapplication.presentation.viewmodel.DetailViewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CreateFragment : Fragment() {

    private lateinit var createViewModel: CreateViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createViewModel = ViewModelProvider(this)[CreateViewModel::class.java]

        return ComposeView(requireContext()).apply {
            setContent {
                CreateReminderScreen(
                    viewModel = createViewModel
                ){
                    goToActionNavigation(R.id.action_CreateFragment_to_HomeFragment)
                }
            }
        }
    }


    private fun goToActionNavigation(resActionId: Int){
        findNavController().navigate(resId = resActionId)
    }

}