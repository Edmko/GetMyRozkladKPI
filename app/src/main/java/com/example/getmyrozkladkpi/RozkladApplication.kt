package com.example.getmyrozkladkpi

import android.app.Application
import android.content.ContextWrapper
import com.example.getmyrozkladkpi.repository.database.AppDatabase
import com.pixplicity.easyprefs.library.Prefs

class RozkladApplication : Application() {
    companion object {
        lateinit var appDatabase: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()
        appDatabase = AppDatabase.getInstance(this)
    }
}