package com.example.getmyrozkladkpi.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class OnFailerDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder
                .setMessage("response failed")
                .setTitle("Error")
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }

        return super.onCreateDialog(savedInstanceState)
    }
}