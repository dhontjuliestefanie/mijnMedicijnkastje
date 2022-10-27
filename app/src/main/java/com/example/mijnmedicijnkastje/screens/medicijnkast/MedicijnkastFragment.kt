package com.example.mijnmedicijnkastje.screens.medicijnkast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mijnmedicijnkastje.R
import com.example.mijnmedicijnkastje.database.MedicijnInKastDatabase
import com.example.mijnmedicijnkastje.databinding.FragmentMedicijnkastBinding
import com.example.mijnmedicijnkastje.util.CustomDialog

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
        val adapter =
            ListMedicijnkastAdapter(MedicijnInKastClickListener { viewModel.clickMedicijn(it) })

        val application = requireNotNull(this.activity).application
        val dataSource = MedicijnInKastDatabase.getInstance(application).medicijnDatabaseDAO
        val fact = MedicijnkastModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, fact).get(MedicijnkastViewModel::class.java)
        binding.medicijnkastViewModel = viewModel

        setHasOptionsMenu(true)

        binding.listMedicijnkast.adapter = adapter
        viewModel.medicijnkast.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = viewLifecycleOwner
        val manager = LinearLayoutManager(activity)
        binding.listMedicijnkast.layoutManager = manager

        adapter.deleteButtonTouched.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                viewModel.medicijn.value = adapter.medicijn.value!!
                deleteMedicijnVanLijst()
            }
        })

        adapter.meerInfoButtonTouched.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                toonMeerInfoMedicijn()
            }
        })

        return binding.root
    }

    private fun toonMeerInfoMedicijn() {
        val newDialog = CustomDialog.newInstance("titeljulie", "subtiteldhont")
        newDialog.show(requireFragmentManager(), "dialogscreen")
        Log.i("InfoDialogScreen", "we zitten hier toch al")
    }

    private fun deleteMedicijnVanLijst() {
        viewModel.delete()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}