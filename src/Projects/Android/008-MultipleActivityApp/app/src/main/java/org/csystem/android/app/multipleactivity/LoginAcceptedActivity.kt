package org.csystem.android.app.multipleactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class LoginAcceptedActivity : AppCompatActivity() {
    private var mUsername: String? = ""
    private var mPassword: String? = ""

    private fun initViews()
    {
        intent.also {
            mUsername = it.getStringExtra("username")
            mUsername.also {
                title = it
            }
            mPassword = it.getStringExtra("password")
        }


    }

    private fun initialize()
    {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_accepted)
        initialize()
    }
}