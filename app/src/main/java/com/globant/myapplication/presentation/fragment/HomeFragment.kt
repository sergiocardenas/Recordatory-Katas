package com.globant.myapplication.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.globant.myapplication.R
import com.globant.myapplication.presentation.screen.HomeScreen
import com.globant.myapplication.presentation.viewmodel.HomeViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
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
                    onItemClicked = { goToActionNavigation(R.id.action_HomeFragment_to_DetailFragment) },
                    onAddItem = { goToActionNavigation(R.id.action_HomeFragment_to_CreateFragment) }
                )
            }
        }

    }

    private fun goToActionNavigation(resActionId: Int){
        findNavController().navigate(resId = resActionId)
    }
}