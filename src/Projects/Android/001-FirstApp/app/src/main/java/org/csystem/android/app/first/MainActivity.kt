package org.csystem.android.app.first

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private fun displayDateTime()
    {
        val now = LocalDateTime.now()

        val msg = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss").format(now)

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun initialize()
    {
        displayDateTime()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }
}