package com.example.mijnmedicijnkastje.screens.user

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mijnmedicijnkastje.R
import com.example.mijnmedicijnkastje.database.UserDatabase
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

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDatabaseDAO
        val fact = UserModelFactory(dataSource, application)

        val adapter = ListUserAdapter(UserClickListener { viewModel.clickUser(it) })
        val dagMedAdapter = DagMedAdapter(DagMedClickListener { viewModel.clickDagMed(it) })

        viewModel = ViewModelProvider(this, fact).get(UserViewModel::class.java)
        binding.userViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.addUserClicked.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                addUser()
            }
        })

        viewModel.addDagMedClicked.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                addDagelijkseMedicatie(dagMedAdapter)
            }
        })

        binding.listUser.adapter = adapter
        val manager = LinearLayoutManager(activity)
        binding.listUser.layoutManager = manager

        val managerDagMedUser = LinearLayoutManager(activity)
        binding.listMedsVanUser.adapter = dagMedAdapter
        binding.listMedsVanUser.layoutManager = managerDagMedUser

        viewModel.users.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.dagMedVanUser.observe(viewLifecycleOwner, Observer {
            it?.let {
                dagMedAdapter.submitList(it)
            }
        })

        dagMedAdapter.deleteButtonTouched.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                viewModel.dagmed.value = dagMedAdapter.medicijn.value!!
                deleteDagelijkseMedicatie()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun deleteDagelijkseMedicatie() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Bent u zeker dat u dit medicijn wil verwijderen?")
        alertDialogBuilder.setPositiveButton("Ja") { alertDialogBuilder, _ -> viewModel.removeDagMedVanUser() }
        alertDialogBuilder.setNegativeButton(android.R.string.no) { _, _ -> null }
        alertDialogBuilder.show()
    }

    private fun addUser() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.fragment_dialog_medicijn)
        val yesBtn = dialog.findViewById(R.id.btnAddUser) as Button
        val noBtn = dialog.findViewById(R.id.btnCancel) as Button
        yesBtn.setOnClickListener {
            val naam =
                dialog.findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            val voornaam = dialog.findViewById<EditText>(R.id.editTextVoornaam).text.toString()
            val geboortedatum =
                dialog.findViewById<EditText>(R.id.editTextGeboortedatum).text.toString()
            viewModel.addUser(naam, voornaam, geboortedatum)
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun addDagelijkseMedicatie(dagMedAdapter: DagMedAdapter) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.fragment_dialog_add_dagmed)
        val yesBtn = dialog.findViewById(R.id.btnAddDagMed) as Button
        val noBtn = dialog.findViewById(R.id.btnCancelAddDagMed) as Button
        yesBtn.setOnClickListener {
            val naamMed =
                dialog.findViewById<EditText>(R.id.etNaamMed).text.toString()
            val tijdstip = dialog.findViewById<EditText>(R.id.etTijdstip).text.toString()
            viewModel.addDagMedVanUser(naamMed, tijdstip)
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}