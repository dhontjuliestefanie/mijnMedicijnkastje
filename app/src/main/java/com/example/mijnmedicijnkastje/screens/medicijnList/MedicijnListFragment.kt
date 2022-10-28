package com.example.mijnmedicijnkastje.screens.medicijnList

import Records
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
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

        val adapter = ListMedicijnAdapter(MedicijnClickListener {
            viewModel.clickMedicijn(it)
        })

        viewModel.medicijn.observe(viewLifecycleOwner, Observer { medicijn ->
            medicijn?.let {
                requireView().findNavController().navigate(
                    MedicijnListFragmentDirections.actionMedicijnListFragment4ToMedicijnFragment3(
                        medicijn
                    )
                )
                viewModel.onMedicijnDetailNavigated()
            }
        })

        binding.listMed.adapter = adapter
        viewModel.medicijnen.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = viewLifecycleOwner
        val manager = LinearLayoutManager(activity)
        binding.listMed.layoutManager = manager
        return binding.root
    }

    private fun navigateMedicijnDetail() {
//        val record = viewModel.medicijn.value!!
//        val medicijn: Medicijn = Medicijn(record.productnaam, record.registratienummer, null, null, null, null)
        val medicijn = viewModel.medicijn.value!!
        findNavController()
            .navigate(
                MedicijnListFragmentDirections.actionMedicijnListFragment4ToMedicijnFragment3(
                    medicijn
                )
            )
        viewModel.navigateToMedicijnDetailFinished()
    }

    private fun createMedicijn() {
        findNavController()
            .navigate(MedicijnListFragmentDirections.actionMedicijnListFragment4ToCreateMedicijnFragment())
        viewModel.createMedicijnFinished()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}

