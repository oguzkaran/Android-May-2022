package org.csystem.android.app.hiltdi.configuration.data

import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import javax.inject.Inject

class DateTimeInfo @Inject constructor (private val mTemporal: Temporal, private val mFormatter: DateTimeFormatter) {
    //...
    override fun toString(): String = mFormatter.format(mTemporal)
}