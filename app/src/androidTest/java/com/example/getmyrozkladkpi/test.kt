package com.example.getmyrozkladkpi
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import com.example.getmyrozkladkpi.repository.database.AppDatabase
import com.example.getmyrozkladkpi.repository.database.dao.LessonDao
import com.example.getmyrozkladkpi.repository.database.entity.Lesson
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
@RunWith(AndroidJUnit4::class)
class SleepDatabaseTest {

    private lateinit var lessonDao: LessonDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        lessonDao = db.lessonDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val lesson = Lesson("1","Monday","1","1","1","1","1","1","1","1","1","1","1","1")
        lessonDao.insertAllLessons(lesson)
        val lesson1 = lessonDao.getAllLessons()
        assertEquals(lesson1[0].day_name, "Monday")
    }
}