package com.example.mijnmedicijnkastje.screens.medicijnList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mijnmedicijnkastje.R
import com.example.mijnmedicijnkastje.databinding.FragmentMedicijnListBinding
import com.example.mijnmedicijnkastje.models.Medicijn

class MedicijnListFragment : Fragment() {

    private lateinit var viewModel: MedicijnListViewModel
    private lateinit var binding: FragmentMedicijnListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentMedicijnListBinding>(
            inflater,
            R.layout.fragment_medicijn_list,
            container,
            false
        )

        val fact = MedicijnListModelFactory()

        viewModel = ViewModelProvider(this, fact).get(MedicijnListViewModel::class.java)
        binding.medicijnListViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToMedicijnDetail.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                navigateMedicijnDetail()
            }
        })

        viewModel.createMedicijn.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                createMedicijn()
            }
        })
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun navigateMedicijnDetail() {
        val medicijn : Medicijn = viewModel.medicijn.value!!
        findNavController()
            .navigate(MedicijnListFragmentDirections.actionMedicijnListFragment4ToMedicijnFragment3(medicijn))
        viewModel.navigateToMedicijnDetailFinished()
    }

    private fun createMedicijn() {
        findNavController()
            .navigate(MedicijnListFragmentDirections.actionMedicijnListFragment4ToCreateMedicijnFragment())
        viewModel.navigateToMedicijnDetailFinished()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}

