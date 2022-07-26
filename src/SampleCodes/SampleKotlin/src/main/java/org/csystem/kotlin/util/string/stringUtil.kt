/*----------------------------------------------------------------------
	FILE        : stringUtil.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 26.07.2022

	Utility functions for string operations

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.string

import kotlin.random.Random

private const val ALPHABET_TR = "abcçdefgğhıijklmnoöprsştuüvyz"
private const val ALPHABET_EN = "abcdefghijklmnopqrstuwxvyz"
private const val ALPHABET_TR_CAPITAL = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ"
private const val ALPHABET_EN_CAPITAL = "ABCDEFGHIJKLMNOPQRSTUWXVYZ"
private const val ALPHABET_TR_ALL = ALPHABET_TR + ALPHABET_TR_CAPITAL
private const val ALPHABET_EN_ALL = ALPHABET_EN + ALPHABET_EN_CAPITAL
private const val DIGITS = "0123456789"

private fun String.isPangram(alphabet: String) : Boolean
{
    for (c in alphabet)
        if (!this.contains(c))
            return false

    return true
}

private fun String.isIsogram(alphabet: String) : Boolean
{
    for (c in alphabet) {
        val count = this.countString(c + "")
        if (count != 1)
            return false
    }

    return true
}

fun String.countString(s: String, ignoreCase: Boolean = false) : Int
{
    var count = 0
    var i = -1

    while (true) {
        i = this.indexOf(s, i + 1, ignoreCase)
        if (i == -1)
            break
        ++count
    }

    return count;
}

fun Random.getRandomTextTR(n: Int) = getRandomText(n, ALPHABET_TR_ALL)

fun Random.getRandomTextEN(n: Int) = getRandomText(n, ALPHABET_EN_ALL)

fun Random.getRandomText(n: Int, str: String) : String
{
    val sb = StringBuilder()

    for (i in 1..n)
        sb.append(str[this.nextInt(str.length)]);

    return sb.toString()
}

fun String.isIsogramTR() = lowercase().isIsogram(ALPHABET_TR)
fun String.isIsogramEN() = lowercase().isIsogram(ALPHABET_EN)
fun String.isPangramTR() = lowercase().isPangram(ALPHABET_TR)
fun String.isPangramEN() = lowercase().isPangram(ALPHABET_EN)

