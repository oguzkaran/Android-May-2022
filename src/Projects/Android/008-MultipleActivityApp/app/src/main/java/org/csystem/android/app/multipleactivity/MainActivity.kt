package org.csystem.android.app.multipleactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private fun loginButtonClicked()
    {
        Intent(this, LoginActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun registerButtonClicked()
    {
        Toast.makeText(this,  "Register", Toast.LENGTH_SHORT).show()
    }

    private fun exitButtonClicked()
    {
        Toast.makeText(this, R.string.goodbye_message_text, Toast.LENGTH_LONG).show()
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onButtonClicked(view: View) = when (view.id)
    {
        R.id.mainActivityButtonLogin -> loginButtonClicked()
        R.id.mainActivityButtonRegister -> registerButtonClicked()
        else -> exitButtonClicked()
    }
}