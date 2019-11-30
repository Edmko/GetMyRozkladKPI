package com.example.getmyrozkladkpi.rozklad

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.getmyrozkladkpi.consts.week
import com.example.getmyrozkladkpi.repository.database.dao.LessonDao
import com.example.getmyrozkladkpi.repository.database.entity.Lesson
import com.pixplicity.easyprefs.library.Prefs


class RozkladViewModel(database: LessonDao) : ViewModel() {

    var lessonList = database.getAllLessons()
    var context : FragmentActivity?=null
    var weekNum = Prefs.getInt(week, 1)

    //Get LessonsList for week
    fun getWeekSchedule(week: Int): MutableList<Lesson> {
        val dayDataWeek = mutableListOf<Lesson>()
        lessonList.value?.forEach {
            Log.d(TAG,lessonList.value.toString())
            if (it.lesson_week.toInt() == week) {
                dayDataWeek.add(it)
            }
        }
        return dayDataWeek
    }
    // Check for Sunday in Schedule
    fun getDayList(week_number: Int): List<String> {
        val dayList =
            mutableListOf("Понеділок", "Вівторок", "Середа", "Четвер", "П’ятниця", "Субота")
        val week = getWeekSchedule(week_number)
        if (week.isNotEmpty()){
            if (week[week.size - 1].day_number.toInt() != 6)
                dayList.removeAt(dayList.size - 1)
        }
        return dayList
    }

    inner class LM(context: Context) : LinearLayoutManager(context) {
        override fun canScrollVertically(): Boolean {
            return false
        }
    }
}
