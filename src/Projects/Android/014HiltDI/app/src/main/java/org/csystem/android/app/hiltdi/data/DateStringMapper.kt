package org.csystem.android.app.hiltdi.data

import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.formatter.LocalDateFormatterAuthInterceptor
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import javax.inject.Inject

class DateStringMapper @Inject constructor(@LocalDateFormatterAuthInterceptor private val mFormatter: DateTimeFormatter) : IMapper<Temporal, String> {
    override fun toType1(t: String) : Temporal =  LocalDate.parse(t, mFormatter)
    override fun toType2(t: Temporal) : String = mFormatter.format(t)
}