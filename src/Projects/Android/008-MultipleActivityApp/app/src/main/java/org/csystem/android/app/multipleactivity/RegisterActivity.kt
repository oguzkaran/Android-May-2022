package org.csystem.android.app.multipleactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.multipleactivity.data.RegisterInfo
import org.csystem.android.app.multipleactivity.databinding.ActivityRegisterBinding
import org.csystem.android.app.multipleactivity.keys.REGISTER_INFO

class RegisterActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityRegisterBinding

    private fun createRegisterInfo() : RegisterInfo
    {
        val name = mBinding.registerActivityEditTextName.text.toString()
        val email = mBinding.registerActivityEditTextEmail.text.toString()
        val username = mBinding.registerActivityEditTextUsername.text.toString()
        val password = mBinding.registerActivityEditTextPassword.text.toString()

        return RegisterInfo(name, email, username, password)
    }

    private fun confirmPassword() = mBinding.registerActivityEditTextPassword.text.toString() == mBinding.registerActivityEditTextConfirmPassword.text.toString()

    private fun registerButtonClickedCallback()
    {
        if (confirmPassword()) {
            val registerInfo = createRegisterInfo()

            //Control data

            Intent(this, RegisterDetailsActivity::class.java).apply {
                putExtra(REGISTER_INFO, registerInfo)
                startActivity(this)
            }
        }
        else
            Toast.makeText(this, "Confirm your password", Toast.LENGTH_LONG).show()
    }


    private fun initAcceptSwitch()
    {
        mBinding.registerActivitySwitchAccept.setOnCheckedChangeListener {
                _, checked -> mBinding.registerActivityButtonRegister.isEnabled = checked
        }
    }

    private fun initRegisterButton()
    {
        mBinding.registerActivityButtonRegister.setOnClickListener{registerButtonClickedCallback()}
    }

    private fun initViews()
    {
        initRegisterButton()
        initAcceptSwitch()
    }


    private fun initBinding()
    {
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
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
        setContentView(R.layout.activity_register)
        initialize()
    }
}