package com.example.mijnmedicijnkastje.screens.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mijnmedicijnkastje.R
import com.example.mijnmedicijnkastje.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentUserBinding


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentUserBinding>(
            inflater,
            R.layout.fragment_user,
            container,
            false
        )

        val fact = UserModelFactory()

        viewModel = ViewModelProvider(this, fact).get(UserViewModel::class.java)
        binding.userViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}