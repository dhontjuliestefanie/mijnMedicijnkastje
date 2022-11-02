package com.example.mijnmedicijnkastje.screens.medicijnkast

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mijnmedicijnkastje.R
import com.example.mijnmedicijnkastje.database.MedicijnInKast
import com.example.mijnmedicijnkastje.database.MedicijnInKastDatabase
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

        performSearch(adapter)

        adapter.deleteButtonTouched.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                viewModel.medicijn.value = adapter.medicijn.value!!
                deleteMedicijnVanLijst()
            }
        })

        adapter.meerInfoButtonTouched.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            val med = adapter.medicijn
            if (it) {
                toonMeerInfoMedicijn(med)
            }
        })
        adapter.increaseAantalTouched.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                viewModel.medicijn.value = adapter.medicijn.value!!
                updateMedicijn()
            }
        })
        adapter.decreaseAantalTouched.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                viewModel.medicijn.value = adapter.medicijn.value!!
                updateMedicijn()
            }
        })

        viewModel.switchActive.observe(viewLifecycleOwner, Observer {
            it
            if (it) {
                toonVervallenProducten(adapter)
            } else {
                toonAlleProducten(adapter)
            }
        })

        return binding.root
    }

    private fun toonMeerInfoMedicijn(medicijnInKast: MutableLiveData<MedicijnInKast?>) {
        var link = medicijnInKast.value?.linkInfo
        if (link.isNullOrBlank()) {
            Toast.makeText(context, "Geen verdere info over medicijn beschikbaar", Toast.LENGTH_LONG).show()
        } else {
            goToUrl(link)
        }
    }

    private fun goToUrl(url: String?) {
        val uriUrl: Uri = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }

    private fun deleteMedicijnVanLijst() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Bent u zeker dat u dit medicijn wil verwijderen?")
        alertDialogBuilder.setPositiveButton("Ja") { alertDialogBuilder, _ -> viewModel.delete() }
        alertDialogBuilder.setNegativeButton(android.R.string.no) { _, _ -> null }
        alertDialogBuilder.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun updateMedicijn() {
        viewModel.update()
    }

    private fun performSearch(adapter: ListMedicijnkastAdapter) {
        binding.searchZoekInMedicijnkastje.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null) {
                    adapter.submitList(null)
                } else {
                    var nieuweLijst = viewModel.getFilterdeLijst(newText, adapter.currentList)
                    adapter.submitList(nieuweLijst)
                }
                return true
            }
        })
    }

    private fun toonVervallenProducten(adapter: ListMedicijnkastAdapter) {
        var lijstVervallenProducten = viewModel.getLijstVervallenProducten(adapter.currentList)
        adapter.submitList(lijstVervallenProducten)
    }

    private fun toonAlleProducten(adapter: ListMedicijnkastAdapter) {
        var alleProducten = viewModel.medicijnkast
        adapter.submitList(alleProducten.value)
    }
}