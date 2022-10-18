package org.csystem.android.app.multipleactivity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.multipleactivity.data.RegisterInfo
import org.csystem.android.app.multipleactivity.data.UsersRepository
import org.csystem.android.app.multipleactivity.data.viewmodel.RegisterInfoViewModel
import org.csystem.android.app.multipleactivity.databinding.ActivityRegisterDetailsBinding
import org.csystem.android.app.multipleactivity.datetime.createBirthDate
import org.csystem.android.app.multipleactivity.keys.REGISTER_INFO
import java.time.LocalDate
import java.time.Month

class RegisterDetailsActivity : AppCompatActivity() {
    private lateinit var mRegisterInfo: RegisterInfoViewModel
    private lateinit var mBinding: ActivityRegisterDetailsBinding

    private fun registerDetailsButtonClickedCallback()
    {
        val birthDate = createBirthDate(mBinding.registerDetailsActivitySpinnerDays.selectedItemPosition + 1,
            mBinding.registerDetailsActivitySpinnerMonths.selectedItemPosition + 1,
            mBinding.registerDetailsActivitySpinnerYears.selectedItem as Int)

        if (birthDate == null) {
            Toast.makeText(this, R.string.invalid_date_toast_message_text, Toast.LENGTH_LONG).show()
            return
        }
        //Refresh mRegisterInfo
        mRegisterInfo.birthDate = birthDate
        Toast.makeText(this, "Register completed!...", Toast.LENGTH_LONG).show()
        //UsersRepository.save(mRegisterInfo)
        finish()
    }

    private fun initEducationInfo()
    {
        mBinding.registerDetailsActivityRadioGroupMaritalStatus.findViewWithTag<RadioButton>(mRegisterInfo.maritalStatus.toString()).isChecked = true
    }

    private fun initMaritalStatusInfo()
    {
        mBinding.registerDetailsActivityRadioGroupEducation.findViewWithTag<RadioButton>(mRegisterInfo.education.toString()).isChecked = true
    }

    private fun initEditTexts()
    {
        mBinding.registerDetailsActivityEditTextName.setText(mRegisterInfo.name)
        mBinding.registerDetailsActivityEditTextEmail.setText(mRegisterInfo.email)
        mBinding.registerDetailsActivityEditTextUsername.setText(mRegisterInfo.username)
    }

    private fun initDaySpinner()
    {
        val days = Array(31) {it + 1}

        mBinding.registerDetailsActivitySpinnerDays.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, days)
        mBinding.registerDetailsActivitySpinnerDays.setSelection(mRegisterInfo.birthDate.dayOfMonth - 1)
    }

    private fun initMonthSpinner()
    {
        mBinding.registerDetailsActivitySpinnerMonths.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Month.values())
        mBinding.registerDetailsActivitySpinnerMonths.setSelection(mRegisterInfo.birthDate.monthValue - 1)
    }

    private fun initYearSpinner()
    {
        val today = LocalDate.now() //Sistemin saati değiştirilmesine karşılık bir server'dan alınabilir. İleride göreceğiz
        val first = today.year - 100 + 1
        val years = Array(100) {first + it}.reversedArray()

        mBinding.registerDetailsActivitySpinnerYears.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        mBinding.registerDetailsActivitySpinnerYears.setSelection(today.year - mRegisterInfo.birthDate.year)
    }

    private fun initBirthDateSpinners()
    {
        initDaySpinner()
        initMonthSpinner()
        initYearSpinner()
    }

    private fun initBackButton() = mBinding.registerDetailsActivityButtonBack.setOnClickListener{finish()}

    private fun initRegisterButton() = mBinding.registerDetailsActivityButtonRegister.setOnClickListener{registerDetailsButtonClickedCallback()}

    private fun initIntentData()
    {
        mRegisterInfo = if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU) {
            Toast.makeText(this, "Before Tiramisu!...", Toast.LENGTH_LONG).show()
            intent.getSerializableExtra(REGISTER_INFO) as RegisterInfoViewModel
        }
        else {
            Toast.makeText(this, "Tiramisu+!...", Toast.LENGTH_LONG).show()
            intent.getSerializableExtra(REGISTER_INFO, RegisterInfoViewModel::class.java)!!
        }
    }

    private fun initViews()
    {
        initIntentData()
        initEditTexts()
        initEducationInfo()
        initMaritalStatusInfo()
        initRegisterButton()
        initBackButton()
        initBirthDateSpinners()
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