package com.example.mijnmedicijnkastje.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.example.mijnmedicijnkastje.R
import com.example.mijnmedicijnkastje.database.MedicijnInKast


class CustomDialog : DialogFragment() {

    companion object {
        fun newInstance(med: MutableLiveData<MedicijnInKast?>): CustomDialog {
            val args = Bundle()
            args.putParcelable("med", med.value)
            val fragment = CustomDialog()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_medicijn, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupView(view)
//        setupClickListeners(view)
//        val med: MedicijnInKast? = savedInstanceState?.getParcelable("med")
//        Log.i("Julie", med?.naam.toString())

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }}

//    private fun setupView(view: View) {
//        view.tvTitle.text = arguments?.getString(KEY_TITLE)
//        view.tvSubTitle.text = arguments?.getString(KEY_SUBTITLE)
//    }
//
//    private fun setupClickListeners(view: View) {
//        view.btnPositive.setOnClickListener {
//            // TODO: Do some task here
//            dismiss()
//    }
//
//        view.btnNegative.setOnClickListener {
//            // TODO: Do some task here
//            dismiss()
//}
