package org.csystem.android.app.hiltdi.application

import android.app.Application
import android.content.Context
import android.widget.Toast
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class HiltDIApplication: Application() {
    companion object { //DI kullanılmazsa Application Context ve Application sınıfı için anlamlı için anlamlı
        private lateinit var mSelf: HiltDIApplication

        fun getApplication() = mSelf
    }

    override fun onCreate()
    {
        Toast.makeText(this, "Application started", Toast.LENGTH_LONG).show()
        mSelf = this
        super.onCreate()
    }
}

