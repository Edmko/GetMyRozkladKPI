package com.example.getmyrozkladkpi.settings

import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.getmyrozkladkpi.R
import com.example.getmyrozkladkpi.RozkladApplication
import com.example.getmyrozkladkpi.consts.group
import com.example.getmyrozkladkpi.databinding.SetGroupFragmentBinding
import com.example.getmyrozkladkpi.repository.database.AppDatabase
import com.example.getmyrozkladkpi.repository.server.ServerCommunicator
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.set_group_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

class SetGroup : Fragment() {
    private lateinit var viewModel: SetGroupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding = SetGroupFragmentBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this).get(SetGroupViewModel::class.java)
        if (Prefs.getString(group, "").isNotEmpty()){this.findNavController().navigate(R.id.rozklad)}
        binding.button.setOnClickListener {
           Prefs.putString(group, binding.setGroup.text.toString())
            RozkladApplication.appDatabase.lessonDao.deleteAllLessons()
            ServerCommunicator(RozkladApplication.appDatabase).getAllData(binding.setGroup.text.toString())
            it.findNavController().navigate(R.id.rozklad)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        set_group.filters = set_group.filters + InputFilter.AllCaps()
        super.onActivityCreated(savedInstanceState)
    }

    override fun onPause() {
        CoroutineScope(Job()).cancel()
        super.onPause()
    }
}
