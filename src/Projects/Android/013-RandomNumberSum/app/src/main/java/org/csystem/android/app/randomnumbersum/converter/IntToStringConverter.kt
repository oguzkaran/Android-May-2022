package org.csystem.android.app.randomnumbersum.converter

import androidx.databinding.InverseMethod

object IntToStringConverter {
    @InverseMethod("stringToInt")
    fun intToString(value: Int) = value.toString()

    fun stringToInt(str: String) : Int
    {
        var result = 0

        try {
            result = str.toInt()
        }
        catch (ignore: NumberFormatException) {

        }

        return result
    }
}