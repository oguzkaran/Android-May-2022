package org.csystem.android.app.multipleactivity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.multipleactivity.data.Education
import org.csystem.android.app.multipleactivity.data.MaritalStatus
import org.csystem.android.app.multipleactivity.data.RegisterInfo
import org.csystem.android.app.multipleactivity.databinding.ActivityRegisterBinding
import org.csystem.android.app.multipleactivity.keys.REGISTER_INFO
import org.csystem.android.app.multipleactivity.keys.TEXT_PASSWORD_HIDE
import org.csystem.android.app.multipleactivity.keys.TEXT_PASSWORD_SHOW
import java.time.DateTimeException
import java.time.LocalDate
import java.time.Month
import java.util.function.ToLongBiFunction

class RegisterActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityRegisterBinding

    private fun getEducation() : Education
    {
        val selected = findViewById<RadioButton>(mBinding.registerActivityRadioGroupEducation.checkedRadioButtonId)

        return Education.valueOf(selected.tag as String)
    }

    private fun getMaritalStatus() : MaritalStatus
    {
        val selected = findViewById<RadioButton>(mBinding.registerActivityRadioGroupMaritalStatus.checkedRadioButtonId)

        return MaritalStatus.valueOf(selected.tag as String)
    }

    private fun createRegisterInfo(birthDate: LocalDate) : RegisterInfo
    {
        val name = mBinding.registerActivityEditTextName.text.toString()
        val email = mBinding.registerActivityEditTextEmail.text.toString()
        val username = mBinding.registerActivityEditTextUsername.text.toString()
        val password = mBinding.registerActivityEditTextPassword.text.toString()

        return RegisterInfo(name, email, username, password, getEducation(), getMaritalStatus(), birthDate)
    }

    private fun confirmPassword() = mBinding.registerActivityEditTextPassword.text.toString() == mBinding.registerActivityEditTextConfirmPassword.text.toString()

    private fun createBirthDate() : LocalDate?
    {
        var result: LocalDate? = null

        try {
            val day = mBinding.registerActivitySpinnerDays.selectedItemPosition + 1
            val month = mBinding.registerActivitySpinnerMonths.selectedItemPosition + 1
            val year = mBinding.registerActivitySpinnerYears.selectedItem as Int
            result = LocalDate.of(year, month, day)
        }
        catch (ignore: DateTimeException) {

        }

        return result
    }

    private fun registerButtonClickedCallback()
    {
        if (confirmPassword()) {
            val birthDate = createBirthDate();

            if (birthDate == null) {
                Toast.makeText(this, "Invalid date format", Toast.LENGTH_LONG).show()
                return
            }
            val registerInfo = createRegisterInfo(birthDate)

            Intent(this, RegisterDetailsActivity::class.java).apply {
                putExtra(REGISTER_INFO, registerInfo)
                startActivity(this)
            }
        }
        else
            Toast.makeText(this, "Confirm your password", Toast.LENGTH_LONG).show()
    }

    private fun showPasswordSwitchCallback(checked: Boolean)
    {
        mBinding.registerActivityEditTextPassword.also {
            it.inputType = if (checked) TEXT_PASSWORD_SHOW else TEXT_PASSWORD_HIDE
        }
    }

    private fun initDaySpinner()
    {
        val days = Array(31) {it + 1}

        mBinding.registerActivitySpinnerDays.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
    }

    private fun initMonthSpinner()
    {
        mBinding.registerActivitySpinnerMonths.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Month.values())
    }

    private fun initYearSpinner()
    {
        val today = LocalDate.now() //Sistemin saati değiştirilmesine karşılık bir server'dan alınabilir. İleride göreceğiz
        val first = today.year - 100 + 1
        val years = Array(100) {first + it}

        mBinding.registerActivitySpinnerYears.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
    }

    private fun initBirthDateSpinners()
    {
        initDaySpinner()
        initMonthSpinner()
        initYearSpinner()
    }

    private fun initAcceptSwitch()
    {
        mBinding.registerActivitySwitchAccept.setOnCheckedChangeListener {
                _, checked -> mBinding.registerActivityButtonRegister.isEnabled = checked
        }
    }

    private fun initShowPasswordSwitch()
    {
        mBinding.registerActivitySwitchShowPassword.setOnCheckedChangeListener {
                _, checked -> showPasswordSwitchCallback(checked)
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
        initShowPasswordSwitch()
        initBirthDateSpinners()
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