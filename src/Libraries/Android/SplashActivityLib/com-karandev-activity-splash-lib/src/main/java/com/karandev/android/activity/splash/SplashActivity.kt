package com.karandev.android.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.karandev.android.activity.splash.intent.IntentFilterUtil

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private fun startCountDownTimer(millisInFuture: Long, countDownInterval: Long, prefix: String = "")
    {
        object: CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(ms: Long)
            {
                title = "$prefix${ms / 1000}"
            }

            override fun onFinish()
            {
                Intent(IntentFilterUtil.SPLASH_FILTER).apply { startActivity(this) }
                finish()
            }
        }.start()
    }

    private fun initCountDownTimer()
    {
        val millisInFuture = resources.getInteger(R.integer.com_karandev_splach_countdown_future).toLong()
        val countDownInterval = resources.getInteger(R.integer.com_karandev_splach_countdown_interval).toLong()
        val prefix = resources.getString(R.string.com_karandev_splash_title_prefix)

        startCountDownTimer(millisInFuture, countDownInterval, prefix)
    }

    private fun initialize()
    {
        setContentView(R.layout.activity_splash)
        initCountDownTimer()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}