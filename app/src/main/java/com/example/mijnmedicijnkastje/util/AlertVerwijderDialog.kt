//package com.example.mijnmedicijnkastje.util
//
//import android.app.AlertDialog
//import android.app.DatePickerDialog
//import android.app.Dialog
//import android.os.Bundle
//import android.view.View
//import androidx.core.content.ContentProviderCompat.requireContext
//import androidx.fragment.app.DialogFragment
//import java.util.*
//
//class AlertVerwijderDialog : DialogFragment(this) {
//
//    companion object {
//        fun newInstance(): AlertDialog {
//            val fragment = AlertDialog(requireContext())
//            return fragment
//        }
//    }
//
//    override fun onCreateDialog(view: View): DialogFragment {
//        val builder = AlertDialog.Builder()
//
//        return DatePickerDialog(requireActivity(), listener, year, month, day)
//    }
//}