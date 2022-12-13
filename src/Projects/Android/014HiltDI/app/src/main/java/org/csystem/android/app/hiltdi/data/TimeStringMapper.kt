package org.csystem.android.app.hiltdi.data

import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.formatter.LocalTimeFormatterAuthInterceptor
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import javax.inject.Inject

class TimeStringMapper @Inject constructor(@LocalTimeFormatterAuthInterceptor private val mFormatter: DateTimeFormatter) : IMapper<Temporal, String> {
    override fun toType1(t: String) : Temporal =  LocalTime.parse(t, mFormatter)
    override fun toType2(t: Temporal) : String = mFormatter.format(t)
}