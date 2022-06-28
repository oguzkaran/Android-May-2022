package org.csystem.kotlin.util.numeric

import kotlin.math.abs
import kotlin.math.log10

private val onesTR = arrayOf("", "bir", "iki", "üç", "dört", "beş", "altı", "yedi", "sekiz", "dokuz")
private val tensTR = arrayOf("", "on", "yirmi", "otuz", "kırk", "elli", "altmış", "yetmiş", "seksen", "doksan")
private val onesEN = arrayOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

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

fun numToStr3DTR(value: Int) : String
{
    if (value == 0)
        return "sıfır"

    val sb = StringBuilder()
    if (value < 0)
        sb.append("eksi")

    return numToStr3DTR(abs(value), sb)
}

fun digits(a: Long)  = digits(a, 1)
fun digitsInTwos(a: Long) = digits(a, 2)
fun digitsInThrees(a: Long) = digits(a, 3)

fun digitsCount(a: Int) = digitsCount(a.toLong())

fun digitsCount(a: Long) = if (a != 0L) log10(abs(a.toDouble())).toInt() + 1 else 1

fun digitsSum(a: Int) : Int
{
    var temp = a
    var sum = 0

    while (temp != 0) {
        sum += temp % 10
        temp /= 10
    }

    return Math.abs(sum)
}

fun getDigitsPowerSum(a: Int) : Int
{
    var temp = a
    var sum = 0
    val n = digitsCount(a).toDouble()

    while (temp != 0) {
        sum += Math.pow((a % 10).toDouble(), n).toInt()
        temp /= 10
    }

    return sum
}

fun getPrime(n: Int) : Long
{
    var count = 0
    var value =  2L

    while (true) {
        if (isPrime(value))
            ++count

        if (count == n)
            return value

        ++value
    }
}

fun isArmstrong(a: Int) = a == getDigitsPowerSum(a)

fun isPalindrome(a: Int) = a == reverse(a)

fun isPrime(a: Long) : Boolean
{
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

fun reverse(a: Int) : Int
{
    var temp = a;
    var rev = 0

    while (temp != 0) {
        rev = rev * 10 + temp % 10
        temp /= 10
    }

    return rev
}
