package com.example.getmyrozkladkpi.rozklad

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.getmyrozkladkpi.R
import kotlinx.android.synthetic.main.rozklad_fragment.*

class Rozklad : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rozklad_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(RozkladViewModel::class.java)
        viewModel.context = activity
        recyclerList.layoutManager = viewModel.LM(activity as Context)
        recyclerList2.layoutManager = viewModel.LM(activity as Context)
        recyclerList.isNestedScrollingEnabled = false
        recyclerList2.isNestedScrollingEnabled = false

        viewModel.dataList.observe(this, Observer {
            recyclerList.adapter =
                RozkladAdapter(activity as Context, viewModel.getDayList(1), viewModel.getWeek(1),viewModel.week.value!!.data)
            recyclerList2.adapter =
                RozkladAdapter(activity as Context, viewModel.getDayList(2), viewModel.getWeek(2),viewModel.week.value!!.data)
        })
        viewModel.getWeekNumber(activity)
        if (viewModel.loadGroup(activity).isNotEmpty()) {
            viewModel.getLessons(activity ,viewModel.loadGroup(activity) , "lessons")
        } else {
            val inflater = activity!!.layoutInflater
            val view = inflater.inflate(R.layout.set_group_dlg, null)
            val builder = AlertDialog.Builder(activity)
            builder
                .setView(view)
                .setTitle(R.string.group_dlg_title)

            builder.setPositiveButton(
                R.string.ok, DialogInterface.OnClickListener(
                    fun(_: DialogInterface, _: Int) {

                        val editGroup = view.findViewById<EditText>(R.id.edit_group)
                        val groupName = editGroup.text.toString()
                        viewModel.saveGroup(activity, groupName)
                        viewModel.getLessons(activity, viewModel.loadGroup(activity), "lessons")
                    }
                )
            )
            val alertDialog = builder.create()
            alertDialog.show()
        }

    }
}


