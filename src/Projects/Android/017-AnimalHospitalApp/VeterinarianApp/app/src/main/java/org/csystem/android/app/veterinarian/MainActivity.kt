package org.csystem.android.app.veterinarian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.veterinarian.databinding.ActivityMainBinding
import org.csystem.android.app.veterinarian.viewmodel.MainActivityViewModel
import org.csystem.android.app.veterinarian.viewmodel.VeterinarianSaveActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityViewModel(this)
    }

    private fun init()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        init()
    }

    fun saveButtonClicked()
    {
        Intent(this, VeterinarianSaveActivity::class.java).apply { startActivity(this) }
    }

    fun getButtonClicked()
    {
        Intent(this, VeterinarianInfoActivity::class.java).apply { startActivity(this) }
    }
}