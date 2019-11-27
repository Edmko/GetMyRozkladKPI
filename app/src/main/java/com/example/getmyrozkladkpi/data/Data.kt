package com.example.getmyrozkladkpi.data

data class Data(
    val day_name: String,
    val day_number: String,
    val group_id: String,
    val lesson_full_name: String,
    val lesson_id: String,
    val lesson_name: String,
    val lesson_number: String,
    val lesson_room: String,
    val lesson_type: String,
    val lesson_week: String,
    val rate: String,
    val rooms: List<Room>,
    val teacher_name: String,
    val teachers: List<Teacher>,
    val time_end: String,
    val time_start: String
)