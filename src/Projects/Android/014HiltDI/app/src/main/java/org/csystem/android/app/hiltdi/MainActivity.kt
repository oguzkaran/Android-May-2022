package org.csystem.android.app.hiltdi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.LocalDateAuthInterceptor
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.formatter.LocalDateFormatterAuthInterceptor
import org.csystem.android.app.hiltdi.configuration.inject.annotattion.mapper.DateStringMapperAuthInterceptor
import org.csystem.android.app.hiltdi.data.DateTimeInfo
import org.csystem.android.app.hiltdi.data.IMapper
import org.csystem.android.app.hiltdi.data.viewmodel.MainActivityViewModel
import org.csystem.android.app.hiltdi.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun firstButtonClicked()
    {
        Intent(this, FirstActivity::class.java).apply { startActivity(this) }
    }

    fun secondButtonClicked()
    {
        Intent(this, SecondActivity::class.java).apply { startActivity(this) }
    }
}