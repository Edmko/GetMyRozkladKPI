package com.example.getmyrozkladkpi.rozklad

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.getmyrozkladkpi.consts.group
import com.example.getmyrozkladkpi.data.Data
import com.example.getmyrozkladkpi.data.Lessons
import com.example.getmyrozkladkpi.data.week.Week
import com.example.getmyrozkladkpi.dialogs.OnFailerDialog
import com.example.getmyrozkladkpi.dialogs.ProgressDialogFragment
import com.example.getmyrozkladkpi.network.RozkladApiObj
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RozkladViewModel : ViewModel() {

    var dataList = MutableLiveData<List<Data>>()
    var progDlg = ProgressDialogFragment()
    var context : FragmentActivity?=null
    var week = MutableLiveData<Week>()


    fun getLessons(context: FragmentActivity?, group:String, request:String){
        if(group.isNotEmpty() and request.isNotEmpty()) RozkladApiObj.retrofitService.getProperties(group, request).enqueue(object: Callback<Lessons>{
            override fun onFailure(call: Call<Lessons>, t: Throwable) {
                OnFailerDialog().show(context!!.supportFragmentManager, "Fail")
            }
            override fun onResponse(call: Call<Lessons>, response: Response<Lessons>) {
                if (response.body()!=null) dataList.postValue(response.body()?.data)
            }
        } )
    }

    fun getWeek(week:Int):MutableList<Data>{
        val dayDataWeek= mutableListOf<Data>()
        dataList.value?.forEach {
            if (it.lesson_week.toInt()==week) {dayDataWeek.add(it)}
        }
        return dayDataWeek
    }

    fun getDayList(week_number:Int): List<String>{
        val dayList = mutableListOf("Понеділок", "Вівторок", "Середа", "Четвер", "П’ятниця", "Субота")
        val week = getWeek(week_number)
        if (week[week.size-1].day_number.toInt()!=6)
            dayList.removeAt(dayList.size-1)
        return dayList
    }
    fun saveGroup(activity: FragmentActivity?,string: String){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?:return
        with(sharedPref.edit()){
            putString(group, string)
            commit()
        }
    }
    fun loadGroup(activity: FragmentActivity?): String{
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        return sharedPref?.getString(group, "")!!
    }
    inner class LM(context: Context) : LinearLayoutManager(context) {
        override fun canScrollVertically(): Boolean {
            return false
        }
    }

    fun getWeekNumber(context: FragmentActivity?){
        progDlg.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progDlg.show(context!!.supportFragmentManager, "Load")
        RozkladApiObj.retrofitService.getWeekNumber().enqueue(object :Callback<Week> {

            override fun onFailure(call: Call<Week>, t: Throwable) {
                OnFailerDialog().show(context.supportFragmentManager, "Fail")
                if (progDlg.isAdded)progDlg.dismiss()
            }

            override fun onResponse(call: Call<Week>, response: Response<Week>) {
                if (response.body()!=null) week.postValue(response.body())
                if (progDlg.isAdded)progDlg.dismiss()
            }
        })
    }
}
