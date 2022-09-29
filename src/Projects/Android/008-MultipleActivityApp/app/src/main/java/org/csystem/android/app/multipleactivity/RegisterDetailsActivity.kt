package org.csystem.android.app.multipleactivity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.multipleactivity.data.RegisterInfo
import org.csystem.android.app.multipleactivity.databinding.ActivityRegisterDetailsBinding
import org.csystem.android.app.multipleactivity.keys.REGISTER_INFO

class RegisterDetailsActivity : AppCompatActivity() {
    private lateinit var mRegisterInfo: RegisterInfo
    private lateinit var mBinding: ActivityRegisterDetailsBinding

    private fun registerDetailsButtonClickedCallback()
    {
        //Control data
        Toast.makeText(this, "Register completed!...", Toast.LENGTH_LONG).show()
        finish()
    }

    private fun initEditTexts()
    {
        mBinding.registerDetailsActivityEditTextName.setText(mRegisterInfo.name)
        mBinding.registerDetailsActivityEditTextEmail.setText(mRegisterInfo.email)
        mBinding.registerDetailsActivityEditTextUsername.setText(mRegisterInfo.username)
    }

    private fun initBackButton()
    {
        mBinding.registerDetailsActivityButtonBack.setOnClickListener{finish()}
    }

    private fun initRegisterButton()
    {
        mBinding.registerDetailsActivityButtonRegister.setOnClickListener{registerDetailsButtonClickedCallback()}
    }

    private fun initIntentData()
    {
        mRegisterInfo = if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU) {
            Toast.makeText(this, "Before Tiramisu!...", Toast.LENGTH_LONG).show()
            intent.getSerializableExtra(REGISTER_INFO) as RegisterInfo
        }
        else {
            Toast.makeText(this, "Tiramisu+!...", Toast.LENGTH_LONG).show()
            intent.getSerializableExtra(REGISTER_INFO, RegisterInfo::class.java)!!
        }
    }

    private fun initViews()
    {
        initIntentData()
        initEditTexts()
        initRegisterButton()
        initBackButton()
    }

    private fun initBinding()
    {
        mBinding = ActivityRegisterDetailsBinding.inflate(layoutInflater)
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