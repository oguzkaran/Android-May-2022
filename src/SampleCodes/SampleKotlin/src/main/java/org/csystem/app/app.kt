/*----------------------------------------------------------------------------------------------------------------------
	Sınıf Çalışması: Parametresi ile aldığı int türden bir sayının basamaklarından oluşan diziyi döndüren digits fonksiyonunu
	yazınız ve aşağıdaki kod ile test ediniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.kotlin.util.array.write
import org.csystem.kotlin.util.console.readInt
import org.csystem.kotlin.util.numeric.digits
import kotlin.random.Random

fun main() = runDigitsTest()

fun runDigitsTest()
{
    val count = readInt("Bir sayı giriniz:")

    for (i in 1..count) {
        val value = Random.nextInt();
        print("$value -> ")
        write(digits(value))
    }

    println("Tekrar yapıyor musunuz?")
}