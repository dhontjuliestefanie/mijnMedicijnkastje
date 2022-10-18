package com.example.mijnmedicijnkastje.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mijnmedicijnkastje.R
import com.example.mijnmedicijnkastje.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var mainModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )

        //        val medicijn = MedicijnFragmentArgs.fromBundle(requireArguments()).medicijn
        val fact = MainModelFactory()

        mainModel = ViewModelProvider(this, fact).get(MainViewModel::class.java)
        binding.mainViewModel = mainModel
        binding.lifecycleOwner = viewLifecycleOwner

        mainModel.navigateToMedicijnLijst.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                navigateMedicijnLijst()
            }
        })

        mainModel.navigateToMedicijnkast.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                navigateMedicijnkast()
            }
        })

        return binding.root
    }

    private fun navigateMedicijnLijst() {
        findNavController()
            .navigate(MainFragmentDirections.actionMainFragmentToMedicijnActivity())
        mainModel.navigateToMedicijnLijstFinished()
    }

    private fun navigateMedicijnkast() {
        findNavController()
            .navigate(MainFragmentDirections.actionMainFragmentToUserActivity2())
        mainModel.navigateToMedicijnLijstFinished()
    }
}