package com.karandev.util.databinding.converter

import androidx.databinding.InverseMethod

object LongStringConverter {
    @InverseMethod("toStr")
    fun toLong(str: String) : Long
    {
        var result = 0L

        try {
            result = str.toLong()
        }
        catch (ignore: NumberFormatException) {

        }

        return result
    }
    fun toStr(value: Long) = value.toString()
}