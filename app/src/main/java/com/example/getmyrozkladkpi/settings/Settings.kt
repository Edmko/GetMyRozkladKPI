package com.example.getmyrozkladkpi.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.getmyrozkladkpi.R
import com.example.getmyrozkladkpi.consts.group
import com.example.getmyrozkladkpi.consts.isFirstLaunch
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.settings_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

class Settings : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        changeGroup.setOnClickListener {
            Prefs.putBoolean(isFirstLaunch, true)
            it.findNavController().navigate(R.id.action_settings_to_setGroup)
        }
    }

    override fun onPause() {
        CoroutineScope(Job()).cancel()
        super.onPause()
    }
}
