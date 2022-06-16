package org.csystem.kotlin.util.string

import java.lang.StringBuilder
import kotlin.random.Random

const val ALPHABET_TR = "abcçdefgğhıijklmnoöprsştuüvyz"
const val ALPHABET_EN = "abcdefghijklmnopqrstuwxvyz"
const val ALPHABET_TR_CAPITAL = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ"
const val ALPHABET_EN_CAPITAL = "ABCDEFGHIJKLMNOPQRSTUWXVYZ"
const val ALPHABET_TR_ALL = ALPHABET_TR + ALPHABET_TR_CAPITAL
const val ALPHABET_EN_ALL = ALPHABET_EN + ALPHABET_EN_CAPITAL
const val DIGITS = "0123456789"

fun getRandomTextTR(n: Int, random: Random = Random) = getRandomText(n, ALPHABET_TR_ALL, random)

fun getRandomTextEN(n: Int, random: Random = Random) = getRandomText(n, ALPHABET_EN_ALL, random)

fun getRandomText(n: Int, str: String, random: Random = Random) : String
{
    val sb = StringBuilder()

    for (i in 1..n)
        sb.append(str[random.nextInt(str.length)]);

    return sb.toString()
}
