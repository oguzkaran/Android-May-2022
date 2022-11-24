package org.csystem.android.app.simplecounter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var mStartText: String
    private lateinit var mStopText: String
    private val mFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss")
    private var mCounterThread: Thread? = null
    private var mCounterScheduler: Scheduler? = null
    private var mClockScheduler: Scheduler? = null //TODO


    private fun setStartStopText()
    {
        if (mBinding.startStopText!! == mStartText)
            mBinding.startStopText = mStopText
        else
            mBinding.startStopText = mStartText
    }

    private fun datetimeSchedulerCallback()
    {
        val now = LocalDateTime.now()
        mBinding.clock = mFormatter.format(now)

        runOnUiThread {
            title = mBinding.clock!!
        }
    }

    private fun counterSchedulerCallback()
    {
        var counter = mBinding.counter2!!.toInt()

        ++counter

        mBinding.counter2 = counter.toString()
    }

    private fun counter1ThreadCallback()
    {
        try {
            while (true) {
                var counter = mBinding.counter1!!.toInt()

                ++counter

                mBinding.counter1 = counter.toString()
                Thread.sleep(1000)
            }
        }
        catch (ignore: InterruptedException) {
            runOnUiThread {
                Toast.makeText(this, "Counter paused", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initResources()
    {
        mStartText = resources.getString(R.string.start_button_text)
        mStopText = resources.getString(R.string.stop_button_text)
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = SimpleCounterViewModel(this)
        mBinding.counter1 = "0"
        mBinding.counter2 = "0"
        mBinding.clock = ""
        mBinding.isEnabled = true
        mBinding.startStopText = mStartText
    }

    private fun initialize()
    {
        initResources()
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

    fun startStopButtonClicked()
    {
        if (mCounterThread != null)
            if (!mCounterThread!!.isAlive)
                mCounterThread = thread{counter1ThreadCallback()}
            else
                mCounterThread!!.interrupt()
        else
            mCounterThread = thread { counter1ThreadCallback() }

        setStartStopText()
    }

    fun startButtonClicked()
    {
        mBinding.isEnabled = false
        mCounterScheduler = Scheduler(1, TimeUnit.SECONDS).schedule { counterSchedulerCallback() }
    }

    fun stopButtonClicked()
    {
        mBinding.isEnabled = true
        mCounterScheduler!!.cancel()
    }
}