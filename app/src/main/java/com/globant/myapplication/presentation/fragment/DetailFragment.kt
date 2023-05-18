package com.globant.myapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.globant.myapplication.presentation.screen.DetailReminderScreen
import com.globant.myapplication.presentation.utils.PresentationConstants.REMAINDER_ID
import com.globant.myapplication.presentation.viewmodel.DetailViewModel
import com.globant.myreminders.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        var currentID = -1
        arguments?.let {
            currentID = it.getInt(REMAINDER_ID, -1)
        }
        detailViewModel.fetchReminder(currentID)

        return ComposeView(requireContext()).apply {
            setContent {
                DetailReminderScreen(
                    viewModel = detailViewModel
                ){
                    goToActionNavigation(R.id.action_DetailFragment_to_HomeFragment)
                }
            }
        }
    }


    private fun goToActionNavigation(resActionId: Int){
        findNavController().navigate(resId = resActionId)
    }

}