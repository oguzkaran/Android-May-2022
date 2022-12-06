package org.csystem.android.app.randomtextgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import org.csystem.android.app.randomtextgenerator.configuration.global.COUNT_DOWN_INTERVAL
import org.csystem.android.app.randomtextgenerator.configuration.global.MILLIS_IN_FUTURE
import org.csystem.android.app.randomtextgenerator.configuration.global.START_VALUE
import org.csystem.util.string.StringUtil

class MainActivity : AppCompatActivity() {
    private fun initSplashCountdownTimer()
    {
        object : CountDownTimer(MILLIS_IN_FUTURE, COUNT_DOWN_INTERVAL) {
            override fun onTick(millisUntilFinished: Long)
            {
                title = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish()
            {
                finish()
                Intent(this@MainActivity, MainMenuActivity::class.java).apply { startActivity(this) }
            }
        }.start()
    }

    private fun initialize()
    {
        setContentView(R.layout.activity_main)
        initSplashCountdownTimer()
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}