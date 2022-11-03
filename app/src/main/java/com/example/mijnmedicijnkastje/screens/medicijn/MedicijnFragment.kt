package com.example.mijnmedicijnkastje.screens.medicijn

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mijnmedicijnkastje.R
import com.example.mijnmedicijnkastje.database.MedicijnInKastDatabase
import com.example.mijnmedicijnkastje.databinding.FragmentMedicijnBinding
import com.example.mijnmedicijnkastje.util.DatePickerFragment


class MedicijnFragment : Fragment() {

    private lateinit var viewModel: MedicijnViewModel
    private lateinit var binding: FragmentMedicijnBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentMedicijnBinding>(
            inflater,
            R.layout.fragment_medicijn,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = MedicijnInKastDatabase.getInstance(application).medicijnDatabaseDAO

        val medicijn = MedicijnFragmentArgs.fromBundle(requireArguments()).medicijn
        val fact = MedicijnModelFactory(medicijn, dataSource, application)

        viewModel = ViewModelProvider(this, fact).get(MedicijnViewModel::class.java)
        binding.medicijnViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToMedicijnkast.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                navigateMedicijnkast()
            }
        })

        viewModel.timePickerDialogData.observe(viewLifecycleOwner, Observer {
            it
            if (it) {
                showDatePickerDialog()
            }
        })

        viewModel.linkToWebsite.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                goToWebsite()
            }
        })
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun showDatePickerDialog() {
        val newFragment =
            DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
                var dayS = ""
                if (day < 10) {
                    dayS = "0" + day
                }
                else {
                    dayS = day.toString()
                }
                val selectedDate = dayS + "/" + (month + 1) + "/" + year
                viewModel.medicijn.value?.houdbaarheidsdatum = selectedDate
                binding.kiesHoudbaarheidsdatum.text = selectedDate
            })
        newFragment.show(requireFragmentManager(), "datum")
        viewModel.btnCalendarDialogClickFinished()
    }

    private fun navigateMedicijnkast() {
        findNavController()
            .navigate(MedicijnFragmentDirections.actionMedicijnFragment3ToUserActivity())
        viewModel.navigateToMedicijnKastFinished()
        Toast.makeText(activity, "Medicijn is toegevoegd", Toast.LENGTH_SHORT).show()
    }

    private fun goToWebsite() {
        goToUrl(viewModel.medicijn.value?.linkInfo)
        viewModel.btnMeerInfoEnBijsluiterClickFinished()
    }

    private fun goToUrl(url: String?) {
        val uriUrl: Uri = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}
