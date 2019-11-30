package com.example.getmyrozkladkpi.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.getmyrozkladkpi.repository.database.entity.Lesson

@Dao
interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLessons(lessons: List<Lesson>)

    @Update
    fun updateLessons(vararg lessons: Lesson)

    @Query("Delete from lessons")
    fun deleteAllLessons()

    @Query("Select * From lessons")
    fun getAllLessons(): LiveData<List<Lesson>>
}