package com.example.getmyrozkladkpi.data

import com.example.getmyrozkladkpi.repository.database.entity.Lesson

data class Lessons(
    val `data`: List<Lesson>,
    val debugInfo: String,
    val message: String,
    val meta: Any,
    val statusCode: Int,
    val timeStamp: Int
)