package org.csystem.android.app.multipleactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.multipleactivity.keys.PASSWORD
import org.csystem.android.app.multipleactivity.keys.USERNAME

class LoginAcceptedActivity : AppCompatActivity() {
    private var mUsername: String? = ""
    private var mPassword: String? = ""

    private fun initViews()
    {
        intent.also {
            mUsername = it.getStringExtra(USERNAME)
            mUsername.also {
                title = it
            }
            mPassword = it.getStringExtra(PASSWORD)
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