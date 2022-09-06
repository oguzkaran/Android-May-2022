package org.csystem.android.app.onbackpressed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var mFinishCount = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed()
    {
        ++mFinishCount
        if (mFinishCount == 2) {
            Toast.makeText(this, "Closing activity", Toast.LENGTH_LONG).show()
            super.onBackPressed()
        }
        else
            Toast.makeText(this, "You must press again for closing", Toast.LENGTH_LONG).show()
    }
}