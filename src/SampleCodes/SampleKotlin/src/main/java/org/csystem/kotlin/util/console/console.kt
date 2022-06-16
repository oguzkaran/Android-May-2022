package org.csystem.kotlin.util.console

fun readInt(msg: String) : Int
{
    print(msg)
    return readLine()!!.toInt()
}

fun readLong(msg: String) : Long
{
    print(msg)
    return readLine()!!.toLong()
}

fun readDouble(msg: String) : Double
{
    print(msg)
    return readLine()!!.toDouble()
}

fun readString(msg: String) : String
{
    print(msg)
    return readLine()!!
}