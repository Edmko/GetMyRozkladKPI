package com.example.getmyrozkladkpi.data

data class Lessons(
    val `data`: List<Data>,
    val debugInfo: String,
    val message: String,
    val meta: Any,
    val statusCode: Int,
    val timeStamp: Int
)