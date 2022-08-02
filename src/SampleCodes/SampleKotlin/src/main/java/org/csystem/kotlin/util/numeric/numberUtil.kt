/*----------------------------------------------------------------------
	FILE        : numberUtil.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 26.07.2022

	Utility functions for numeric operations

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.numeric

import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.min

private val onesTR = arrayOf("", "bir", "iki", "üç", "dört", "beş", "altı", "yedi", "sekiz", "dokuz")
private val tensTR = arrayOf("", "on", "yirmi", "otuz", "kırk", "elli", "altmış", "yetmiş", "seksen", "doksan")
private val onesEN = arrayOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

private fun digits(a: Long, n: Int) : IntArray
{
    var temp = abs(a)
    val count = if (a != 0L) (log10(temp.toDouble()) / n).toInt() + 1 else 1
    val result = IntArray(count)
    val powOfTen = Math.pow(10.0, n.toDouble()).toLong()

    for (i in count - 1 downTo 0) {
        result[i] = (temp % powOfTen).toInt()
        temp /= powOfTen
    }

    return result
}


private fun numToStr3DTR(value: Int, sb: StringBuilder) : String
{
    val a = value / 100
    val b = value / 10 % 10
    val c = value % 10

    if (a != 0) {
        if (a != 1)
            sb.append(onesTR[a])
        sb.append("yüz")
    }

    if (b != 0)
        sb.append(tensTR[b])

    if (c != 0)
        sb.append(onesTR[c])

    return sb.toString()
}

private fun numToStr3DTR(value: Int) : String
{
    if (value == 0)
        return "sıfır"

    val sb = StringBuilder()
    if (value < 0)
        sb.append("eksi")

    return numToStr3DTR(abs(value), sb)
}

fun Long.digits()  = digits(this, 1)
fun Long.digitsInTwos() = digits(this, 2)
fun Long.digitsInThrees() = digits(this, 3)

fun Int.digitsCount() = this.toLong().digitsCount()

fun Long.digitsCount() = if (this != 0L) log10(abs(this.toDouble())).toInt() + 1 else 1

fun Int.digitsSum() : Int
{
    var temp = this
    var sum = 0

    while (temp != 0) {
        sum += temp % 10
        temp /= 10
    }

    return Math.abs(sum)
}

fun Int.getDigitsPowerSum() : Int
{
    var temp = this
    var sum = 0
    val n = temp.digitsCount().toDouble()

    while (temp != 0) {
        sum += Math.pow((temp % 10).toDouble(), n).toInt()
        temp /= 10
    }

    return sum
}

fun getPrime(n: Int) : Long
{
    var count = 0
    var value =  2L

    while (true) {
        if (value.isPrime())
            ++count

        if (count == n)
            return value

        ++value
    }
}

fun Int.isArmstrong() = this == this.getDigitsPowerSum()

fun Int.isPalindrome() = this == this.reverse()

fun Int.isPrime() = this.toLong().isPrime()
fun Long.isPrime() : Boolean
{
    val a = this

    if (a <= 1)
        return false

    if (a % 2 == 0L)
        return a == 2L

    if (a % 3 == 0L)
        return a == 3L

    if (a % 5 == 0L)
        return a == 5L

    if (a % 7 == 0L)
        return a == 7L

    var i = 11L

    while (i * i <= a) {
        if (a % i == 0L)
            return false
        i += 2
    }

    return true
}

fun mid(a: Int, b: Int, c: Int) : Int
{
    if (b in a..c || b in c..a)
        return b

    if (a in b..c || a in c..b)
        return a

    return c
}

fun Long.numToTextTR() : String
{
    TODO("Homework-012-3")
}

fun Long.numToTextEN() : String
{
    TODO("Write if you have a time")
}


fun Int.reverse() : Int
{
    var temp = this;
    var rev = 0

    while (temp != 0) {
        rev = rev * 10 + temp % 10
        temp /= 10
    }

    return rev
}
////////////////////////////////////////////////////////////////
infix fun Int.gcd(a: Int) : Int
{
    val min = min(abs(this), abs(a))

    for (i in min downTo 2)
        if (a % i == 0 && this % i == 0)
            return i

    return 1
}

fun getFibonacciNumber(n: Int) : Int /*x*/
{
    if (n <= 2)
        return n - 1

    var prev2 = 0
    var prev1 = 1
    var result = 0

    for (i in 1..n - 2) {
        result = prev1 + prev2
        prev2 = prev1
        prev1 = result
    }

    return result
}

fun getNextArmstrong(a: Int) : Int /*x*/
{
    var temp = a + 1

    while (!temp.isArmstrong())
        temp++

    return temp
}

fun getNextFibonacciNumber(a: Int) : Int /*x*/
{
    var prev2 = 0
    var prev1 = 1
    var result = 0

    while (result <= a) {
        result = prev1 + prev2
        prev2 = prev1
        prev1 = result
    }

    return result
}

