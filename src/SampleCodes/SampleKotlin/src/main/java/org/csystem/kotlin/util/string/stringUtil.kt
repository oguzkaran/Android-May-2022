package org.csystem.kotlin.util.string

import java.lang.StringBuilder
import kotlin.random.Random

private const val ALPHABET_TR = "abcçdefgğhıijklmnoöprsştuüvyz"
private const val ALPHABET_EN = "abcdefghijklmnopqrstuwxvyz"
private const val ALPHABET_TR_CAPITAL = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ"
private const val ALPHABET_EN_CAPITAL = "ABCDEFGHIJKLMNOPQRSTUWXVYZ"
private const val ALPHABET_TR_ALL = ALPHABET_TR + ALPHABET_TR_CAPITAL
private const val ALPHABET_EN_ALL = ALPHABET_EN + ALPHABET_EN_CAPITAL
private const val DIGITS = "0123456789"

private fun isPangram(s: String, alphabet: String) : Boolean
{
    for (c in alphabet)
        if (!s.contains(c))
            return false

    return true
}

private fun isIsogram(s: String, alphabet: String) : Boolean
{
    for (c in alphabet) {
        val count = countString(s, c + "")
        if (count != 1)
            return false
    }

    return true
}

fun countString(s1: String, s2: String, ignoreCase: Boolean = false) : Int
{
    var count = 0
    var i = -1

    while (true) {
        i = s1.indexOf(s2, i + 1, ignoreCase)
        if (i == -1)
            break
        ++count
    }

    return count;
}

fun getRandomTextTR(n: Int, random: Random = Random) = getRandomText(n, ALPHABET_TR_ALL, random)

fun getRandomTextEN(n: Int, random: Random = Random) = getRandomText(n, ALPHABET_EN_ALL, random)

fun getRandomText(n: Int, str: String, random: Random = Random) : String
{
    val sb = StringBuilder()

    for (i in 1..n)
        sb.append(str[random.nextInt(str.length)]);

    return sb.toString()
}

fun isIsogramTR(s: String) = isIsogram(s.lowercase(), ALPHABET_TR)
fun isIsogramEN(s: String) = isIsogram(s.lowercase(), ALPHABET_EN)
fun isPangramTR(s: String) = isPangram(s.lowercase(), ALPHABET_TR)
fun isPangramEN(s: String) = isPangram(s.lowercase(), ALPHABET_EN)

