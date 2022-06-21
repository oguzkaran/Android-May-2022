/*----------------------------------------------------------------------------------------------------------------------
   Sınıf Çalışması: Parametresi ile aldığı Int türden bir dizinin elemanlarını stdout'a yazdıran write isimli
   fonksiyonu ve aldığı Int türden count, min ve max ile random isimlei Random türden parametreleri count elemanlı
   elemanları [min, max]  aralığında rasgele değerlerle doldurulmuş bir dizi referansına geri dönen randomIntArray isimli
   fonksiyonu arrayUtil.kt içerisinde yazınız ve aşağıdaki kod ile test ediniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.kotlin.util.array.randomIntArray
import org.csystem.kotlin.util.array.write
import org.csystem.kotlin.util.console.readInt

fun main()
{
    runRandomIntArrayPrintTest();
}

fun runRandomIntArrayPrintTest()
{
    val min = readInt("Minimum değeri giriniz:")
    val max = readInt("Minimum değeri giriniz:")


    while (true) {
        val count = readInt("Dizinin eleman sayısını giriniz:")

        if (count <= 0)
            break
        val a = randomIntArray(count, min, max)

        for (value in a)
            print("$value ")
        println()
        write(a)
    }

    println("Tekrar yapıyor musunuz?")
}