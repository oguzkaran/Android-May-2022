package org.csystem.android.app.multipleactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.multipleactivity.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding

    private fun loginButtonClickedCallback()
    {
        //Control data
        Intent(this, LoginAcceptedActivity::class.java).apply {
            putExtra("username", mBinding.loginActivityEditTextUsername.text.toString())
            putExtra("password", mBinding.loginActivityEditTextUsername.text.toString())
            startActivity(this)
        }
    }

    private fun initAcceptSwitch()
    {
        mBinding.loginActivitySwitchAccept.setOnCheckedChangeListener {
                _, checked -> mBinding.loginActivityButtonLogin.isEnabled = checked
        }
    }

    private fun initLoginButton()
    {
        mBinding.loginActivityButtonLogin.setOnClickListener{loginButtonClickedCallback()}
    }

    private fun initViews()
    {
        initLoginButton()
        initAcceptSwitch()
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