package com.karandev.util.databinding.converter

import androidx.databinding.InverseMethod

object DoubleStringConverter {
    @InverseMethod("toStr")
    fun toDouble(str: String) : Double
    {
        var result = 0.0

        try {
            result = str.toDouble()
        }
        catch (ignore: NumberFormatException) {

        }

        return result
    }
    fun toStr(value: Double) = value.toString()
}