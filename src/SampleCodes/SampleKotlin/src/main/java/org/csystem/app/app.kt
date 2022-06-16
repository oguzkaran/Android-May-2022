/*----------------------------------------------------------------------------------------------------------------------
    Sınıf Çalışması: Parametresi ile aldığı n sayısı kadar içerisinde rakam, İngilizce karakteler, Türkçe
    karakterler ve çeşitli noktalama karakterlerinin rasgele bulunacağı (_-?@.) şifre üreten getRandomPassword
    fonksiyonunu yazınız ve test ediniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.kotlin.util.console.readInt
import org.csystem.kotlin.util.string.ALPHABET_EN_ALL
import org.csystem.kotlin.util.string.ALPHABET_TR_ALL
import org.csystem.kotlin.util.string.DIGITS
import kotlin.random.Random

fun main() = runGetRandomPasswordTest()

fun runGetRandomPasswordTest()
{
    while (true) {
        val n = readInt("Bir sayı giriniz:")

        if (n <= 0)
            break

        println("Text:${getRandomPassword(n)}")
    }

    println("Tekrar yapıyor musunuz?")
}

fun getRandomPassword(n: Int, random: Random = Random) = getRandomText(n, ALPHABET_EN_ALL + ALPHABET_TR_ALL + DIGITS + "_-?@.")
fun getRandomText(n: Int, str: String, random: Random = Random) : String
{
    val sb = StringBuilder()

    for (i in 1..n)
        sb.append(str[random.nextInt(str.length)]);

    return sb.toString()
}

