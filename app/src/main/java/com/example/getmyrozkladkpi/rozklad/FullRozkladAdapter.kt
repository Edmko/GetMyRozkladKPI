package com.example.getmyrozkladkpi.rozklad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.getmyrozkladkpi.R
import com.example.getmyrozkladkpi.data.Data
import kotlinx.android.synthetic.main.day_card.view.*

class FullRozkladAdapter (private val context: Context,private val data: ArrayList<Data>):
    RecyclerView.Adapter<FullRozkladAdapter.FullRozkladViewHolder>(){


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FullRozkladViewHolder, position: Int) {
        holder.lesson.text=data[position].lesson_name
        holder.lessonNumber.text=data[position].lesson_number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullRozkladViewHolder {
        return FullRozkladViewHolder(LayoutInflater.from(context).inflate(R.layout.day_card, parent,false))
    }

    inner class FullRozkladViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lesson = itemView.lesson!!
        val lessonNumber = itemView.lesson_number!!
    }
}

