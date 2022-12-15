package org.csystem.android.app.hiltdi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.LocalDateAuthInterceptor
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.formatter.LocalDateFormatterAuthInterceptor
import org.csystem.android.app.hiltdi.configuration.inject.annotattion.mapper.DateStringMapperAuthInterceptor
import org.csystem.android.app.hiltdi.data.DateTimeInfo
import org.csystem.android.app.hiltdi.data.IMapper
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import javax.inject.Inject


@AndroidEntryPoint
class FirstActivity : AppCompatActivity() {
    @Inject
    lateinit var dateTimeInfo: DateTimeInfo

    @Inject
    @LocalDateAuthInterceptor
    lateinit var date: Temporal

    @LocalDateFormatterAuthInterceptor
    @Inject
    lateinit var dateFormatter: DateTimeFormatter

    @DateStringMapperAuthInterceptor
    @Inject
    lateinit var dateStringMapper: IMapper<Temporal, String>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        Toast.makeText(this, "FirstActivity -> DateTimeInfo:${dateTimeInfo}", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "FirstActivity -> Info: ${dateFormatter.format(date)}", Toast.LENGTH_LONG).show()
        Toast.makeText(this, "FirstActivity -> Mapper Info: ${dateStringMapper.toType2(date)}", Toast.LENGTH_LONG).show()
    }
}