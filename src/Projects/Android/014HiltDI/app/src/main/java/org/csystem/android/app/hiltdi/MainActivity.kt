package org.csystem.android.app.hiltdi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.hiltdi.configuration.data.DateTimeInfo
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var dateTimeInfo: DateTimeInfo
    @Inject lateinit var dateTime: Temporal
    @Inject lateinit var dateTimeFormatter: DateTimeFormatter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "DateTimeInfo:${dateTimeInfo}", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "Info: ${dateTimeFormatter.format(dateTime)}", Toast.LENGTH_LONG).show()
    }
}