package org.csystem.kotlin.util.numeric

import kotlin.math.abs
import kotlin.math.log10

fun digits(a: Int) : IntArray
{
    val d = IntArray(digitsCount(a))
    var temp = abs(a)

    for (i in d.size - 1 downTo 0) {
        d[i] = temp % 10;
        temp /= 10
    }

    return d
}


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
