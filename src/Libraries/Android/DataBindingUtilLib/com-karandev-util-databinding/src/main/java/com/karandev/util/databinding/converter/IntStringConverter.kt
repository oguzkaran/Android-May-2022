package com.karandev.util.databinding.converter

import androidx.databinding.InverseMethod

object IntStringConverter {
    @InverseMethod("toStr")
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