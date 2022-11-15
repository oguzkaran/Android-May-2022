package org.csystem.android.app.quadraticequation.viewmodel.converter

import androidx.databinding.InverseMethod

object DoubleStringConverter {
    fun toStr(value: Double) = value.toString()

    @InverseMethod("toStr")
    fun toDouble(str: String) : Double
    {
        var result = 0.0

        try {
            result = str.toDouble()
        }
        catch (ignore: NumberFormatException) {

        }

        return result;
    }
}