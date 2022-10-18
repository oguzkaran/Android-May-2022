package org.csystem.android.app.multipleactivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.multipleactivity.data.LoginInfo
import org.csystem.android.app.multipleactivity.databinding.ActivityLoginAcceptedBinding
import org.csystem.android.app.multipleactivity.keys.LOGIN_INFO

class LoginAcceptedActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginAcceptedBinding
    private var mCount = 0

    private fun initIntentData()
    {
        intent.also {
            mBinding.loginInfo = if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU)
                intent.getSerializableExtra(LOGIN_INFO) as LoginInfo
            else
                intent.getSerializableExtra(LOGIN_INFO, LoginInfo::class.java)!!
        }
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_accepted)
    }

    private fun initViews()
    {
        title = mBinding.loginInfo!!.username //Burada name gösterilecektir
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

    fun loginAcceptedActivityButtonBackCallback()
    {
        if (++mCount == 1)
            Toast.makeText(this, R.string.return_back_message, Toast.LENGTH_LONG).show()
        else
            finish()
    }
}