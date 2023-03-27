package org.csystem.kotlin.util.console

import java.math.BigDecimal

fun readInt(msg: String = "", errMsg: String = "") : Int
{
    while (true) {
        try {
            print(msg)
            return readln().toInt()
        }
        catch (ignore: NumberFormatException) {
            print(errMsg)
        }
    }
}

fun readLong(msg: String = "", errMsg: String = "") : Long
{
    while (true) {
        try {
            print(msg)
            return readln().toLong()
        }
        catch (ignore: NumberFormatException) {
            print(errMsg)
        }
    }
}

fun readShort(msg: String = "", errMsg: String = "") : Short
{
    while (true) {
        try {
            print(msg)
            return readln().toShort()
        }
        catch (ignore: NumberFormatException) {
            print(errMsg)
        }
    }
}

fun readByte(msg: String = "", errMsg: String = "") : Byte
{
    while (true) {
        try {
            print(msg)
            return readln().toByte()
        }
        catch (ignore: NumberFormatException) {
            print(errMsg)
        }
    }
}

fun readDouble(msg: String = "", errMsg: String = "") : Double
{
    while (true) {
        try {
            print(msg)
            return readln().toDouble()
        }
        catch (ignore: NumberFormatException) {
            print(errMsg)
        }
    }
}

fun readFloat(msg: String = "", errMsg: String = "") : Float
{
    while (true) {
        try {
            print(msg)
            return readln().toFloat()
        }
        catch (ignore: NumberFormatException) {
            print(errMsg)
        }
    }
}

fun readBoolean(msg: String = "") : Boolean = readLine()!!.toBoolean()

fun readString(msg: String = "") : String
{
    print(msg)
    return readln()
}


fun readBigDecimal(msg: String, errMsg: String = "") : BigDecimal
{
    while (true) {
        try {
            print(msg)
            return BigDecimal(readln())
        }
        catch (ignore: NumberFormatException) {
            println(errMsg)
        }
    }
}