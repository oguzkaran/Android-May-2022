package com.karandev.util.databinding.converter

import androidx.databinding.InverseMethod
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object LocalDateToStringConverterTR {
    private var mFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    @InverseMethod("toStr")
    fun toLocalDate(str: String) : LocalDate
    {
        var result = LocalDate.now()

        try {
            result = LocalDate.parse(str, mFormatter)
        }
        catch (ignore: DateTimeParseException) {

        }

        return result
    }
    fun toStr(date:LocalDate) : String = mFormatter.format(date)
}