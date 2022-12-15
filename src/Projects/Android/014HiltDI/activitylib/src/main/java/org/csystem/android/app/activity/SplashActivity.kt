package org.csystem.android.app.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer


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
                Intent("org.csystem.android.app.hiltdi.MainActivity").apply { startActivity(this) }
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