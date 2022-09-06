package org.csystem.android.app.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private fun displayCreateReport(savedInstanceState: Bundle?)
    {
        Toast.makeText(this, if (savedInstanceState == null) "Created now" else "Created Before", Toast.LENGTH_LONG).show()
    }

    private fun initialize(savedInstanceState: Bundle?)
    {
        displayCreateReport(savedInstanceState)
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize(savedInstanceState)
    }

    override fun onStart()
    {
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart()
    {
        super.onRestart()
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume()
    {
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause()
    {
        super.onPause()
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop()
    {
        super.onStop()
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }
}