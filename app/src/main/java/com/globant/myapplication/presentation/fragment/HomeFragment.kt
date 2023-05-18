package com.globant.myapplication.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.globant.myapplication.presentation.screen.HomeScreen
import com.globant.myapplication.presentation.utils.PresentationConstants.REMAINDER_ID
import com.globant.myapplication.presentation.viewmodel.HomeViewModel
import com.globant.myreminders.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreen(
                    viewModel = homeViewModel,
                    onItemClicked = { id -> goToDetail(id) },
                    onAddItem = { goToActionNavigation(R.id.action_HomeFragment_to_CreateFragment) }
                )
            }
        }

    }

    override fun onStart() {
        super.onStart()
        homeViewModel.getReminderList()
    }

    private fun goToDetail(reminderID: Int){
        val params = Bundle()
        params.putInt(REMAINDER_ID, reminderID)
        findNavController().navigate(
            resId = R.id.action_HomeFragment_to_DetailFragment,
            args = params
        )
    }

    private fun goToActionNavigation(resActionId: Int){
        findNavController().navigate(resId = resActionId)
    }
}