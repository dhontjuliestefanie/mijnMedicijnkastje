package com.example.mijnmedicijnkastje.screens.createMedicijn

import android.app.DatePickerDialog
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
import com.example.mijnmedicijnkastje.databinding.FragmentCreateMedicijnBinding
import com.example.mijnmedicijnkastje.util.DatePickerFragment

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

        val application = requireNotNull(this.activity).application
        val dataSource = MedicijnInKastDatabase.getInstance(application).medicijnDatabaseDAO
        val fact = CreateMedicijnModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, fact).get(CreateMedicijnViewModel::class.java)
        binding.createMedicijnViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.voegToeAanMedicijnkast.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                voegToeAanMedicijnkast()
            }
        })

        viewModel.timePickerDialogData.observe(viewLifecycleOwner, Observer {
            if (it) {
                showDatePickerDialog()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun voegToeAanMedicijnkast() {
//        viewModel.aantal.value = Integer.parseInt(binding.invulveldAantal.text.toString())
        findNavController()
            .navigate(CreateMedicijnFragmentDirections.actionCreateMedicijnFragmentToUserActivity())
        Toast.makeText(activity, "Medicijn is toegevoegd", Toast.LENGTH_LONG).show()
        viewModel.navigateToMedicijnKastFinished()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


    private fun showDatePickerDialog() {
        val newFragment =
            DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
                val selectedDate = day.toString() + "/" + (month + 1) + "/" + year
                viewModel.houdbaarheidsdatum.value = selectedDate
                binding.kiesHoudbaarheidsdatum.text = selectedDate
            })
        newFragment.show(requireFragmentManager(), "datePicker")
        viewModel.btnCalendarDialogClickFinished()
    }
}