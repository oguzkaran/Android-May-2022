package org.csystem.android.app.birthdate

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.birthdate.databinding.ActivityMainBinding
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun toLocalDate(str: String) : LocalDate //Bu metot ileride bir kütüphanede olacak
    {
        val formatters = arrayOf(DateTimeFormatter.ofPattern("dd-MM-yyyy"), DateTimeFormatter.ofPattern("dd/MM/yyyy"), DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        for (formatter in formatters)
            try {
                return LocalDate.parse(str, formatter)
            }
            catch (ignore: DateTimeException) {

            }

        throw DateTimeException("Invalid Date format")
    }

    private fun getBirthDayMessage(birthDay: LocalDate, today: LocalDate) : String
    {
        var message = "Doğum gününüz kutlu olsun"

        if (birthDay.isBefore(today))
            message = "Geçmiş doğum günününüz kutlu olsun"
        else if (birthDay.isAfter(today))
            message = "Doğum gününüz şimdiden kutlu olsun"

        return message
    }

    private fun okButtonClickedCallback()
    {
        mBinding.mainActivityTextViewMessage.text = ""
        val today = LocalDate.now()

        try {
            val birthDate = toLocalDate(mBinding.mainActivityEditTextBirthDate.text.toString())
            val birthDay = birthDate.withYear(today.year)
            val message = getBirthDayMessage(birthDay, today)

            val age = ChronoUnit.DAYS.between(birthDate, today) / 365.0

            "%s-> Yaşınız:%.2f".format(message, age).apply {
                mBinding.mainActivityTextViewMessage.text = this
            }
        }
        catch (ignore: DateTimeException) {
            Toast.makeText(this, R.string.invalid_date_format_message, Toast.LENGTH_LONG).show()
        }
    }

    private fun exitButtonClickedCallback() = finish()


    private fun initViews()
    {
        mBinding.mainActivityButtonOK.setOnClickListener {okButtonClickedCallback()}
        mBinding.mainActivityButtonExit.setOnClickListener {exitButtonClickedCallback()}
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
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