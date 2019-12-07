package com.example.getmyrozkladkpi.repository.server

import android.content.ContentValues.TAG
import android.util.Log
import com.example.getmyrozkladkpi.consts.week
import com.example.getmyrozkladkpi.data.Lessons
import com.example.getmyrozkladkpi.data.Week
import com.example.getmyrozkladkpi.repository.database.AppDatabase
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServerCommunicator(private val mainDatabase: AppDatabase) {

    fun getAllData(group: String) {
        GlobalScope.launch {
            getLessons(group)
            getWeekNumber()
        }
    }

    // Get LessonsList from API
    private fun getLessons(group: String) {
        RozkladApiObj.retrofitService.getProperties(group, "lessons")
            .enqueue(object : Callback<Lessons> {
                override fun onFailure(call: Call<Lessons>, t: Throwable) {
                    Log.d(TAG, "fail") //TODO do masage of fail
                }

                override fun onResponse(call: Call<Lessons>, response: Response<Lessons>) {
                    GlobalScope.launch {
                        if (response.body() != null) mainDatabase.lessonDao.insertAllLessons(
                            response.body()!!.data
                        )
                    }
                }
            })
    }

    //Get number of week from API
    private fun getWeekNumber() {
        RozkladApiObj.retrofitService.getWeekNumber().enqueue(object : Callback<Week> {

            override fun onFailure(call: Call<Week>, t: Throwable) {
                Log.d(TAG, "fail") //TODO do masage of fail
            }

            override fun onResponse(call: Call<Week>, response: Response<Week>) {
                if (response.body() != null) Prefs.putInt(week, response.body()?.data!!.toInt())
            }
        })
    }
}

