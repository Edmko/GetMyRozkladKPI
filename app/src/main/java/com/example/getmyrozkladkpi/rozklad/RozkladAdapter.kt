package com.example.getmyrozkladkpi.rozklad

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getmyrozkladkpi.R
import com.example.getmyrozkladkpi.repository.database.entity.Lesson
import kotlinx.android.synthetic.main.rozklad_card.view.*
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList


class RozkladAdapter(
    private val context: Context,
    private val days: List<String>,
    private val data: List<Lesson>,
    private val weekNow: Int
) :
    RecyclerView.Adapter<RozkladAdapter.RozkladViewHolder>() {
    private var viewPool = RecyclerView.RecycledViewPool()

    override fun getItemCount(): Int {

        return days.size

    }

    override fun onBindViewHolder(holder: RozkladViewHolder, position: Int) {
        val fullRozkladLayoutManager = LinearLayoutManager(holder.list.context)
        if (data.isNotEmpty()) {
            holder.day.text = days[position]
            holder.date.text = getDayDate(
                days[position],
                weekNow,
                data[1].lesson_week.toInt())//data[1].lesson.week is week of this schedule
            holder.list.apply {
                layoutManager = fullRozkladLayoutManager
                adapter = FullRozkladAdapter(context, getLesson(data, days, position))
                setRecycledViewPool(viewPool)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RozkladViewHolder {
        return RozkladViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rozklad_card,
                parent,
                false
            )
        )
    }

    inner class RozkladViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day: TextView = itemView.day_name
        val list: RecyclerView = itemView.lessonsList
        val date: TextView = itemView.day_date
    }

    //List of lessons for each day
    private fun getLesson(
        data: List<Lesson>,
        days: List<String>,
        position: Int
    ): ArrayList<Lesson> {
        val list = ArrayList<Lesson>()
        data.forEach {
            if (it.day_name == days[position]) list.add(it)
        }
        return list
    }

    private fun getDayDate(dayOfWeek: String, weekNow: Int, aimWeek: Int): String {
        val days = listOf("Понеділок", "Вівторок", "Середа", "Четвер", "П’ятниця", "Субота")
        val day = days.indexOf(dayOfWeek) + 2 //dayOfWeek start from 1 index and Sunday
        val calendar = Calendar.getInstance()
        val dayOfWeekNow = calendar[Calendar.DAY_OF_WEEK]
        val add = day - dayOfWeekNow
        val addWeek = aimWeek - weekNow
        calendar.add(Calendar.WEEK_OF_MONTH, addWeek)
        calendar.add(Calendar.DAY_OF_MONTH, add)
        return DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.time)
    }
}