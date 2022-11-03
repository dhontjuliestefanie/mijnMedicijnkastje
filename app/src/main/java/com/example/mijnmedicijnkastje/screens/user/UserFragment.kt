package com.example.mijnmedicijnkastje.screens.user

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

        viewModel = ViewModelProvider(this, fact).get(UserViewModel::class.java)
        binding.userViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.addUserClicked.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                addUser()
            }
        })

        binding.listUser.adapter = adapter
        val manager = LinearLayoutManager(activity)
        binding.listUser.layoutManager = manager

        viewModel.users.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        setHasOptionsMenu(true)

        return binding.root
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}