package com.karandev.util.databinding.converter

import androidx.databinding.InverseMethod
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object LocalTimeToStringConverter {
    private var mFormatter = DateTimeFormatter.ofPattern("kk:mm:ss")

    @InverseMethod("toStr")
    fun toLocalTime(str: String) : LocalTime
    {
        var result = LocalTime.now()

        try {
            result = LocalTime.parse(str, mFormatter)
        }
        catch (ignore: DateTimeParseException) {

        }

        return result
    }

    fun toStr(time: LocalTime) : String = mFormatter.format(time)
}