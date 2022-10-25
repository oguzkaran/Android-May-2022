package org.csystem.android.app.cinema

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.cinema.data.CinemaType
import org.csystem.android.app.cinema.data.entity.Cinema
import org.csystem.android.app.cinema.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun getYearInfo() : Triple<Int, Boolean, String>
    {
        var result: Triple<Int, Boolean, String>

        try {
            result = Triple(mBinding.mainActivityEditTextYear.text.toString().toInt(), true, "")
        }
        catch (ignore: java.lang.NumberFormatException) {
            result = Triple(0, false, "Invalid year value")
        }

        return result
    }

    private fun getMessage(name: String, yearInfo: Triple<Int, Boolean, String>, director: String, company: String) : String
    {
        val msgBuilder = StringBuilder()

        if (name.isBlank())
            msgBuilder.append("Name can not be empty").append('\n')
        if (!yearInfo.second)
            msgBuilder.append(yearInfo.third).append('\n')
        if (director.isBlank())
            msgBuilder.append("Director can not be empty").append('\n')
        if (company.isBlank())
            msgBuilder.append("Company can not be empty").append('\n')

        return msgBuilder.toString()
    }

    private fun createCinema() : Pair<Cinema?, String>
    {
        val name = mBinding.mainActivityEditTextName.text.toString().trim()
        val yearInfo = getYearInfo()
        val type = mBinding.mainActivitySpinnerType.selectedItemPosition
        val director = mBinding.mainActivityEditTextDirector.text.trim().toString()
        val company = mBinding.mainActivityEditTextCompany.text.trim().toString()

        val msg = getMessage(name, yearInfo, director, company)

        if (msg.isNotEmpty())
            return Pair(null, msg)

        return Pair(Cinema(name, type, yearInfo.first, director, company), "")
    }

    private fun saveButtonClickCallback()
    {
        val (cinema, msg) = createCinema();

        if (cinema != null) {
            CinemaRepository.save(cinema)
            Toast.makeText(this, "Count:${CinemaRepository.count()}", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun listButtonClickCallback()
    {
        Intent(this, ListCinemaActivity::class.java).apply { startActivity(this) }
    }

    private fun exitButtonClickCallback()
    {
        Toast.makeText(this, R.string.message_text_goodbye_csd, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun initButtons()
    {
        mBinding.mainActivityButtonSave.setOnClickListener { saveButtonClickCallback() }
        mBinding.mainActivityButtonList.setOnClickListener { listButtonClickCallback() }
        mBinding.mainActivityButtonExit.setOnClickListener { exitButtonClickCallback() }
    }

    private fun initTypeSpinner()
    {
        val values = CinemaType.values()
        val types = values.map{it.resourceName}.map{resources.getString(it)}.toTypedArray()

        mBinding.mainActivitySpinnerType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
    }

    private fun initViews()
    {
        initTypeSpinner()
        initButtons()
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