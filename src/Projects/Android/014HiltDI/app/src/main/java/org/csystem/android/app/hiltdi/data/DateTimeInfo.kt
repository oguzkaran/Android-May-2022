package org.csystem.android.app.hiltdi.data


import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.LocalDateTimeAuthInterceptor
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.formatter.LocalDateFormatterAuthInterceptor
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import javax.inject.Inject

class DateTimeInfo @Inject constructor (@LocalDateTimeAuthInterceptor private val mTemporal: Temporal,
                                        private val mFormatter: DateTimeFormatter) {
    //...
    override fun toString(): String = mFormatter.format(mTemporal)
}