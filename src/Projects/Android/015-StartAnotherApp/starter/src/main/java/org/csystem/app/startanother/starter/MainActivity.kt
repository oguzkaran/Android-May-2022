package org.csystem.app.startanother.starter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private fun sendCommand(command: String)
    {
        try {
            Intent("org.csystem.app.startanother.action.shared").apply {
                putExtra("COMMAND", command)
                startActivity(this)
            }
        }
        catch (ex: Throwable) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendCommand("REVERSE AnKaRa")
    }
}