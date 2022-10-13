package org.csystem.android.app.multipleactivity.datetime

import java.time.DateTimeException
import java.time.LocalDate

fun createBirthDate(day: Int, month: Int, year: Int) : LocalDate?
{
    var result: LocalDate? = null

    try{
        result = LocalDate.of(year, month, day)
    }
    catch (ignore: DateTimeException) {

    }

    return result
}