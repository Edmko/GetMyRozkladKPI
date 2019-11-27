package com.example.getmyrozkladkpi.settings

import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.getmyrozkladkpi.databinding.SetGroupFragmentBinding
import kotlinx.android.synthetic.main.set_group_fragment.*
import java.util.*

class SetGroup : Fragment() {

    private lateinit var viewModel: SetGroupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = SetGroupFragmentBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this).get(SetGroupViewModel::class.java)
        binding.button.setOnClickListener {
            viewModel.saveGroup(activity, binding.setGroup.text.toString())
            it.findNavController().navigateUp()
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        set_group.filters = set_group.filters + InputFilter.AllCaps()
        super.onActivityCreated(savedInstanceState)
    }
}
