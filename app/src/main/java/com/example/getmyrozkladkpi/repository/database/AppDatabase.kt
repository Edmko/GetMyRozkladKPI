package com.example.getmyrozkladkpi.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.getmyrozkladkpi.repository.database.dao.LessonDao
import com.example.getmyrozkladkpi.repository.database.entity.Lesson

@Database(entities = [Lesson::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val lessonDao: LessonDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {

                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "lessonDB"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
