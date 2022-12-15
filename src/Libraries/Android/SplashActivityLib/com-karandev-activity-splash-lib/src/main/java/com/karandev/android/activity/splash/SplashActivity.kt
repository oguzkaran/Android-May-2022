package com.karandev.android.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.karandev.android.activity.splash.intent.KaranIntentFilter

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private fun startCountDownTimer()
    {
        object: CountDownTimer(10000, 1000) {
            override fun onTick(ms: Long)
            {
                title = (ms / 1000).toString();
            }

            override fun onFinish()
            {
                Intent(KaranIntentFilter.SPLASH_FILTER).apply { startActivity(this) }
                finish()
            }
        }.start()
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startCountDownTimer()
    }
}