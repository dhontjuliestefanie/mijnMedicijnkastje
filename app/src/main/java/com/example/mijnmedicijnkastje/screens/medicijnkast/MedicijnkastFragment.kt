package com.example.mijnmedicijnkastje.screens.medicijnkast

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
import androidx.navigation.ui.NavigationUI
import com.example.mijnmedicijnkastje.R
import com.example.mijnmedicijnkastje.databinding.FragmentMedicijnkastBinding

class MedicijnkastFragment : Fragment() {

    private lateinit var viewModel: MedicijnkastViewModel
    private lateinit var binding: FragmentMedicijnkastBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentMedicijnkastBinding>(
            inflater,
            R.layout.fragment_medicijnkast,
            container,
            false
        )

        val fact = MedicijnkastModelFactory()

        viewModel = ViewModelProvider(this, fact).get(MedicijnkastViewModel::class.java)
        binding.medicijnkastViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.verwijderMedicijnUitKast.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                verwijderMedicijnUitLijst()
            }
        })

        viewModel.meerInfoMedicijn.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                toonMeerInfoMedicijn()
            }
        })
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun verwijderMedicijnUitLijst() {
        Toast.makeText(activity, "Medicijn is verwijderd", Toast.LENGTH_SHORT).show()
    }

    private fun toonMeerInfoMedicijn() {
        Toast.makeText(activity, "Dialoogvenster in opmaak", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}