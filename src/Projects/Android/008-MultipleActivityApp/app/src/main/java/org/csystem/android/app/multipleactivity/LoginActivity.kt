package org.csystem.android.app.multipleactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.multipleactivity.databinding.ActivityLoginBinding
import org.csystem.android.app.multipleactivity.keys.PASSWORD
import org.csystem.android.app.multipleactivity.keys.USERNAME

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding

    private fun loginButtonClickedCallback()
    {
        //Control data
        Intent(this, LoginAcceptedActivity::class.java).apply {
            putExtra(USERNAME, mBinding.loginActivityEditTextUsername.text.toString())
            putExtra(PASSWORD, mBinding.loginActivityEditTextUsername.text.toString())
            startActivity(this)
        }
    }

    private fun initLoginButton()
    {
        mBinding.loginActivityButtonLogin.setOnClickListener{loginButtonClickedCallback()}
    }

    private fun initViews()
    {
        initLoginButton()
    }

    private fun initBinding()
    {
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBinding()
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }


}