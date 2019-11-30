package com.example.getmyrozkladkpi.settings

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.getmyrozkladkpi.consts.group

class SetGroupViewModel : ViewModel() {


    fun saveGroup(activity: FragmentActivity?, string: String){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?:return
        with(sharedPref.edit()){
            putString(group, string)
            commit()
        }
    }
}
