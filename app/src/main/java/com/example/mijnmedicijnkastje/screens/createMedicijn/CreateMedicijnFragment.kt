package com.example.mijnmedicijnkastje.screens.createMedicijn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mijnmedicijnkastje.R
import com.example.mijnmedicijnkastje.databinding.FragmentCreateMedicijnBinding

class CreateMedicijnFragment : Fragment() {

    private lateinit var viewModel: CreateMedicijnViewModel
    private lateinit var binding: FragmentCreateMedicijnBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentCreateMedicijnBinding>(
            inflater,
            R.layout.fragment_create_medicijn,
            container,
            false
        )

        //        val medicijn = MedicijnFragmentArgs.fromBundle(requireArguments()).medicijn
        val fact = CreateMedicijnModelFactory()

        viewModel = ViewModelProvider(this, fact).get(CreateMedicijnViewModel::class.java)
        binding.createMedicijnViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.voegToeAanMedicijnkast.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                voegToeAanMedicijnkast()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun voegToeAanMedicijnkast() {
        findNavController()
            .navigate(CreateMedicijnFragmentDirections.actionCreateMedicijnFragmentToUserActivity())
        viewModel.navigateToMedicijnKastFinished()
        Toast.makeText(activity, "Medicijn is toegevoegd", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}