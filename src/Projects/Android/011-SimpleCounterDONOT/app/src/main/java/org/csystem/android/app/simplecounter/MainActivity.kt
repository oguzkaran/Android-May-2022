package org.csystem.android.app.simplecounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.simplecounter.databinding.ActivityMainBinding
import org.csystem.android.app.simplecounter.viewmodel.SimpleCounterViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = SimpleCounterViewModel(this)
        mBinding.counter = "0"
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

    fun startButtonClicked()
    {
        while (true) { //Sonsuz döngü dolayısıyla akış "activity mesaj döngüsüne" geri dönmüyor. Bu da "hung process" durumuna yol açar
            var counter = mBinding.counter!!.toLong()

            ++counter

            mBinding.counter = counter.toString()
            Thread.sleep(1000)
        }
    }
}