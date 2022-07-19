package org.csystem.kotlin.util.console

fun readInt(msg: String = "", errMsg: String = "") : Int
{
    while (true) {
        try {
            print(msg)
            return readLine()!!.toInt()
        }
        catch (ex: NumberFormatException) {
            print(errMsg)
        }
    }
}

fun readLong(msg: String = "", errMsg: String = "") : Long
{
    while (true) {
        try {
            print(msg)
            return readLine()!!.toLong()
        }
        catch (ex: NumberFormatException) {
            print(errMsg)
        }
    }
}

fun readShort(msg: String = "", errMsg: String = "") : Short
{
    while (true) {
        try {
            print(msg)
            return readLine()!!.toShort()
        }
        catch (ex: NumberFormatException) {
            print(errMsg)
        }
    }
}

fun readByte(msg: String = "", errMsg: String = "") : Byte
{
    while (true) {
        try {
            print(msg)
            return readLine()!!.toByte()
        }
        catch (ex: NumberFormatException) {
            print(errMsg)
        }
    }
}

fun readDouble(msg: String = "", errMsg: String = "") : Double
{
    while (true) {
        try {
            print(msg)
            return readLine()!!.toDouble()
        }
        catch (ex: NumberFormatException) {
            print(errMsg)
        }
    }
}

fun readFloat(msg: String = "", errMsg: String = "") : Float
{
    while (true) {
        try {
            print(msg)
            return readLine()!!.toFloat()
        }
        catch (ex: NumberFormatException) {
            print(errMsg)
        }
    }
}

fun readBoolean(msg: String = "") : Boolean = readLine()!!.toBoolean()

fun readString(msg: String) : String
{
    print(msg)
    return readLine()!!
}