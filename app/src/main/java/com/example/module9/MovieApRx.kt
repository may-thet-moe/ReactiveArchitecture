package com.example.module9

import android.app.Application
import com.example.module9.data.models.MovieModelImpl

class MovieApRx: Application() {
    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)
    }
}