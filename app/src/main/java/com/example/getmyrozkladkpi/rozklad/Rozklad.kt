package com.example.getmyrozkladkpi.rozklad

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.getmyrozkladkpi.R
import com.example.getmyrozkladkpi.RozkladApplication
import com.example.getmyrozkladkpi.repository.database.AppDatabase
import kotlinx.android.synthetic.main.rozklad_fragment.*

class Rozklad : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rozklad_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModelFactory = RozkladViewModelFactory(RozkladApplication.appDatabase.lessonDao)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(RozkladViewModel::class.java)

        viewModel.context = activity
        recyclerList.layoutManager = viewModel.LM(activity as Context)
        recyclerList2.layoutManager = viewModel.LM(activity as Context)
        recyclerList.isNestedScrollingEnabled = false
        recyclerList2.isNestedScrollingEnabled = false

        viewModel.lessonList.observe(this, Observer {

            recyclerList.adapter =
                RozkladAdapter(
                    activity as Context,
                    viewModel.getDayList(1),
                    viewModel.getWeekSchedule(1),
                    viewModel.weekNum
                )
            recyclerList2.adapter =
                RozkladAdapter(
                    activity as Context,
                    viewModel.getDayList(2),
                    viewModel.getWeekSchedule(2),
                    viewModel.weekNum
                )
        })

    }
}


