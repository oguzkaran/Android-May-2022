package org.csystem.android.app.simplecounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.simplecounter.databinding.ActivityMainBinding
import org.csystem.android.app.simplecounter.viewmodel.SimpleCounterViewModel
import org.csystem.util.scheduler.Scheduler
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val mFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss")
    private var mCounterThread: Thread? = null

    private fun datetimeSchedulerCallback()
    {
        //title = mFormatter.format(LocalDateTime.now())
        mBinding.mainActivityTextViewClock.text = mFormatter.format(LocalDateTime.now())
    }

    private fun counterSchedulerCallback()
    {
        var counter = mBinding.counter2!!.toInt()

        ++counter

        mBinding.counter2 = counter.toString()
    }

    private fun counter1ThreadCallback()
    {
        while (true) {
            var counter = mBinding.counter1!!.toInt()

            ++counter

            mBinding.counter1 = counter.toString()
            Thread.sleep(1000)
        }
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = SimpleCounterViewModel(this)
        mBinding.counter1 = "0"
        mBinding.counter2 = "0"
        mBinding.isEnabled = true
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
        val scheduler = Scheduler(1, TimeUnit.SECONDS)

        scheduler.schedule { datetimeSchedulerCallback() }
        super.onResume()
    }

    fun startButtonClicked1()
    {
        if (mCounterThread == null || !mCounterThread!!.isAlive) {
            mCounterThread = thread{counter1ThreadCallback()}
        }
    }

    fun startButtonClicked2()
    {
        mBinding.isEnabled = false;

        val scheduler = Scheduler(1, TimeUnit.SECONDS)

        scheduler.schedule { counterSchedulerCallback() }
    }
}