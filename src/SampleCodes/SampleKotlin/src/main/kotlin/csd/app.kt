/*----------------------------------------------------------------------------------------------------------------------
    Sınıf Çalışması: Parametresi ile aldığı Int türden bir sayının basamak sayısını döndüren digitsCount isimli
    fonksiyonu yazınız ve aşağıdaki kod ile test ediniz
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    runDigitsCountTest()
}

fun runDigitsCountTest()
{
    while (true) {
        val a = readInt()

        println("$a sayısının basamak sayısı:${digitsCount(a)}")

        if (a == 0)
            break
    }

    println("Tekrar yapıyor musnuz?")
}

fun readInt() = readLine()!!.toInt()



fun digitsCount(a: Int) : Int
{
    TODO("Write this function")
}