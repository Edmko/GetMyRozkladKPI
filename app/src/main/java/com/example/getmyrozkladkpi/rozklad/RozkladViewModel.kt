package com.example.getmyrozkladkpi.rozklad

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.getmyrozkladkpi.consts.week
import com.example.getmyrozkladkpi.repository.database.dao.LessonDao
import com.example.getmyrozkladkpi.repository.database.entity.Lesson
import com.pixplicity.easyprefs.library.Prefs


class RozkladViewModel(database: LessonDao) : ViewModel() {

    var lessonList = database.getAllLessons()
    var context: FragmentActivity? = null
    var weekNum = Prefs.getInt(week, 1)

    //Get LessonsList for week
    fun getWeekSchedule(week: Int): MutableList<Lesson> {
        val dayDataWeek = mutableListOf<Lesson>()
        lessonList.value?.forEach {
            if (it.lesson_week.toInt() == week) {
                dayDataWeek.add(it)
            }
        }
        return dayDataWeek
    }

    //Get day list for week
    fun getDayList(week_number: Int): ArrayList<String> {
        val dayList = ArrayList<String>()
        getWeekSchedule(week_number).forEach {
            if (!dayList.contains(it.day_name))
                dayList.add(it.day_name)
        }
        return dayList
    }

    inner class LM(context: Context) : LinearLayoutManager(context) {
        override fun canScrollVertically(): Boolean {
            return false
        }
    }
}
