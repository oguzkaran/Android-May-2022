package org.csystem.android.app.hiltdi.application

import android.app.Application
import android.widget.Toast
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class HiltDIApplication: Application() {
    override fun onCreate()
    {
        Toast.makeText(this, "Application started", Toast.LENGTH_LONG).show()
        super.onCreate()
    }
}

