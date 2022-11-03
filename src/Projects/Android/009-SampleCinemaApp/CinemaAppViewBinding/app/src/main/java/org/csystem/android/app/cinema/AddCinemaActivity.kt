package org.csystem.android.app.cinema

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.cinema.data.CinemaType
import org.csystem.android.app.cinema.data.entity.Cinema
import org.csystem.android.app.cinema.databinding.ActivityAddCinemaBinding

class AddCinemaActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityAddCinemaBinding

    private fun getYearInfo() : Triple<Int, Boolean, String>
    {
        var result: Triple<Int, Boolean, String>

        try {
            result = Triple(mBinding.addCinemaActivityEditTextYear.text.toString().toInt(), true, "")
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
        val name = mBinding.addCinemaActivityEditTextName.text.toString().trim()
        val yearInfo = getYearInfo()
        val type = mBinding.addCinemaActivitySpinnerType.selectedItemPosition
        val director = mBinding.addCinemaActivityEditTextDirector.text.trim().toString()
        val company = mBinding.addCinemaActivityEditTextCompany.text.trim().toString()

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
        if (CinemaRepository.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle(R.string.message_title_no_cinema)
                .setMessage(R.string.message_text_no_cinema)
                .setPositiveButton(R.string.message_text_no_cinema_ok_button) { _, _ ->  }
                .create().show()
        }
        else
            Intent(this, ListCinemaActivity::class.java).apply { startActivity(this) }
    }

    private fun initButtons()
    {
        mBinding.addCinemaActivityButtonSave.setOnClickListener { saveButtonClickCallback() }
        mBinding.addCinemaActivityButtonList.setOnClickListener { listButtonClickCallback() }
        mBinding.addCinemaActivityButtonExit.setOnClickListener { finish() }
    }

    private fun initTypeSpinner()
    {
        val values = CinemaType.values()
        val types = values.map{it.resourceName}.map{resources.getString(it)}.toTypedArray()

        mBinding.addCinemaActivitySpinnerType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
    }

    private fun initViews()
    {
        initTypeSpinner()
        initButtons()
    }

    private fun initBinding()
    {
        mBinding = ActivityAddCinemaBinding.inflate(layoutInflater)
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