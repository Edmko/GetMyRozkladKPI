package com.example.getmyrozkladkpi.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "lessons")
data class Lesson(
    @PrimaryKey
    @SerializedName("lesson_id")
val lesson_id: String,
    @SerializedName("day_name")
val day_name: String,
    @SerializedName("day_number")
val day_number: String,
    @SerializedName("group_id")
val group_id: String,
    @SerializedName("lesson_full_name")
val lesson_full_name: String,
    @SerializedName("lesson_name")
val lesson_name: String,
    @SerializedName("lesson_number")
val lesson_number: String,
    @SerializedName("lesson_room")
val lesson_room: String,
    @SerializedName("lesson_type")
val lesson_type: String,
    @SerializedName("lesson_week")
val lesson_week: String,
    @SerializedName("rate")
val rate: String,
    @SerializedName("teacher_name")
val teacher_name: String,
    @SerializedName("time_end")
val time_end: String,
    @SerializedName("time_start")
val time_start: String)
