package com.example.getmyrozkladkpi.rozklad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.getmyrozkladkpi.repository.database.dao.LessonDao

class RozkladViewModelFactory(
    private val dataSource: LessonDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RozkladViewModel::class.java)) {
            return RozkladViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}