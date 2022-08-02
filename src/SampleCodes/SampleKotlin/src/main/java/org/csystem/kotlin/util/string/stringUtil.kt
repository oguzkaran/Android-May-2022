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

/////////////////////////////////////////////////////////////////////////////////////

fun String.capitalize() : String
{

    return if (this.isEmpty()) "" else this[0].uppercase() + this.substring(1, this.length).lowercase()
}

fun String.changeCase() : String
{
    val sb = StringBuilder()

    for (ch in this)
        sb.append(if (ch.isLowerCase()) ch.uppercase() else ch.lowercase())

    return sb.toString()

}

fun String.containsAll(text: String ) : Boolean
{
    for (ch in text)
        if (!this.contains(ch))
            return false
    return true
}

fun String.isPalindrome() : Boolean
{
    var left = 0
    var right = this.length - 1

    val str = this.lowercase()

    while (left < right) {
        val chLeft : Char = str[left]
        if (!chLeft.isLetter()) { ++left; continue }

        val chRight : Char = str[right]
        if (!chRight.isLetter()) { --right; continue }

        if (chLeft != chRight) return false

        ++left; --right;
    }
    return true
}

fun String.getLongestPalindrome() : String
{
    var result = ""
    var end = this.length

    while (end != 0) {
        var begin = 0

        while (begin != end) {
            val str = this.substring(begin++, end)

            if (str.length > 1 && str.isPalindrome() && str.length > result.length)
                result = str
        }
        end--
    }
    return result
}

fun String.isIdentifier() : Boolean
{
    if (isBlank() || equals("_"))
        return false

    if (!this[0].isJavaIdentifierStart())
        return false

    for (ch in this.substring(1))
        if (!ch.isJavaIdentifierPart())
            return false

    return true
}

fun String.padLeading(length: Int, ch: Char) : String
{
    val len = this.length

    return if (length <= len) this else (ch + "").repeat(length - len) + this

}

fun String.padLeading(length: Int) = this.padLeading(length, ' ' )

fun String.padTrailing(length: Int, ch: Char) : String
{
    val len = this.length

    return if (length <= len) this else this + (ch + "").repeat(length - len)

}
fun String.padTrailing(length: Int) = this.padTrailing(length, ' ' )

fun String.reversed() : String
{
    val sb = StringBuilder(this)

    sb.reverse()

    return sb.toString()
}

fun String.trimLeading() : String
{
    val sb = StringBuilder()

    for (ch in this)
        if (!ch.isWhitespace()) sb.append(ch) else break

    return sb.toString()
}
fun String.trimTrailing() : String
{
    var i = this.length

    while(i >= 0) {
        --i
        if(!this[i].isWhitespace()) break
    }
    return this.substring(0, i + 1)


}
fun String.wrapWith(begin: String, end : String, strip: Boolean) : String
{
    return String.format("$begin${if (strip) trim() else this}$end")
}

fun String.wrapWith(begin: Char, end : Char, strip: Boolean) = this.wrapWith(begin + "", end + "", strip)

fun String.wrapWith(begin: String, end : String) = this.wrapWith(begin , end , false)

fun String.wrapWith(begin: Char, end : Char) = this.wrapWith(begin + "" , end + "")

fun String.wrapWithBraces() = this.wrapWith('(', ')', true)


