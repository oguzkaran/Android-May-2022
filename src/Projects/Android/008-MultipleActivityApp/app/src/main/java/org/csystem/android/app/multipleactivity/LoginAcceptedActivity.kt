package org.csystem.android.app.multipleactivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.multipleactivity.data.LoginInfo
import org.csystem.android.app.multipleactivity.databinding.ActivityLoginAcceptedBinding
import org.csystem.android.app.multipleactivity.keys.LOGIN_INFO

class LoginAcceptedActivity : AppCompatActivity() {
    private lateinit var mLoginInfo: LoginInfo
    private lateinit var mBinding: ActivityLoginAcceptedBinding
    private var mCount = 0

    private fun initIntentData()
    {
        intent.also {
            mLoginInfo = if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU)
                intent.getSerializableExtra(LOGIN_INFO) as LoginInfo
            else
                intent.getSerializableExtra(LOGIN_INFO, LoginInfo::class.java)!!
        }
    }

    private fun loginAcceptedActivityButtonBackCallback()
    {
        if (++mCount == 1)
            Toast.makeText(this, R.string.return_back_message, Toast.LENGTH_LONG).show()
        else
            finish()
    }

    private fun initBackButton()
    {
        mBinding.loginAcceptedActivityButtonBack.setOnClickListener {loginAcceptedActivityButtonBackCallback()}
    }

    private fun initBinding()
    {
        mBinding = ActivityLoginAcceptedBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initViews()
    {
        initBackButton()
        title = mLoginInfo.username //Burada name gösterilecektir
        mBinding.loginAcceptedActivityTextViewUsername.text = mLoginInfo.username
    }

    private fun initialize()
    {
        initBinding()
        initIntentData()
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        initialize()
    }
}