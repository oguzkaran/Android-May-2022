package org.csystem.android.app.multipleactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.multipleactivity.data.LoginInfo
import org.csystem.android.app.multipleactivity.databinding.ActivityLoginBinding
import org.csystem.android.app.multipleactivity.keys.LOGIN_INFO

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding

    private fun loginButtonClickedCallback()
    {
        //Control data
        Intent(this, LoginAcceptedActivity::class.java).apply {
            putExtra(LOGIN_INFO, mBinding.loginInfo)
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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.loginInfo = LoginInfo()
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