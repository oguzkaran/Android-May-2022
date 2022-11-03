package org.csystem.android.app.cinema.viewmodel.converter

import androidx.databinding.InverseMethod

object IntStringConverter {
    @InverseMethod(value = "toStr")
    fun toInt(str: String) : Int
    {
        var result = 0

        try {
            result = str.toInt()
        }
        catch (ignore: NumberFormatException) {

        }
        return result
    }

    fun toStr(value: Int) = value.toString()
}