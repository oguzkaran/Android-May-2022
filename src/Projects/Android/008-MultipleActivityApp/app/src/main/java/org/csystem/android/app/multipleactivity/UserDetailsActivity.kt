package org.csystem.android.app.multipleactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.multipleactivity.data.RegisterInfo
import org.csystem.android.app.multipleactivity.databinding.ActivityUserDetailsBinding
import org.csystem.android.app.multipleactivity.keys.LOGIN_INFO
import org.csystem.android.app.multipleactivity.keys.REGISTER_INFO

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityUserDetailsBinding

    private fun getRegisterInfoByIntent() : RegisterInfo
    {
        return intent.run {
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU)
                intent.getSerializableExtra(REGISTER_INFO) as RegisterInfo
            else
                intent.getSerializableExtra(REGISTER_INFO, RegisterInfo::class.java)!!
        }
    }

    private fun initRegisterInfoViews(ri: RegisterInfo)
    {
        mBinding.userDetailsActivityTextViewName.text = ri.name
        mBinding.userDetailsActivityTextViewEmail.text = ri.email
        mBinding.userDetailsActivityTextViewUsername.text = ri.username
        mBinding.userDetailsActivityTextViewEducation.text = ri.education.toString()
        mBinding.userDetailsActivityTextViewMaritalStatus.text = ri.maritalStatus.toString()
    }

    private fun initOKButton()
    {
        mBinding.userDetailsActivityButtonOK.setOnClickListener { finish() }
    }

    private fun initViews()
    {
        initRegisterInfoViews(getRegisterInfoByIntent())
        initOKButton()
    }

    private fun initBinding()
    {
        mBinding = ActivityUserDetailsBinding.inflate(layoutInflater)
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