package org.csystem.android.app.randomnumbersum

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.randomnumbersum.databinding.ActivityMainBinding
import org.csystem.android.app.randomnumbersum.viewmodel.MainActivityViewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.random.Random

const val MILLIS = 1000L

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mExecutorService: ExecutorService

    private fun findTotalThreadCallback()
    {
        var result = 0
        val min = mBinding.viewModel!!.min
        val bound = mBinding.viewModel!!.bound
        mBinding.progressResult = ""
        mBinding.total = ""

        try {
            for (i in 1..mBinding.viewModel!!.count) {
                val a = Random.nextInt(min, bound)

                mBinding.progressResult =
                    "${mBinding.progressResult}${if (mBinding.progressResult == "") a else ", $a"}"
                result += a
                Thread.sleep(MILLIS)
            }
            mBinding.total = result.toString()
        }
        catch (ignore: InterruptedException) {
            runOnUiThread {Toast.makeText(this, "Thread must be stopped anymore!....", Toast.LENGTH_LONG).show()}
        }
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityViewModel(this)
        mBinding.progressResult = ""
        mBinding.total = ""
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

    override fun onResume()
    {
        mExecutorService = Executors.newSingleThreadExecutor()
        super.onResume()
    }
    override fun onPause()
    {
        mExecutorService.shutdownNow()
        super.onPause()
    }

    fun okButtonClicked() = mExecutorService.execute {findTotalThreadCallback()}
}