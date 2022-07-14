/*----------------------------------------------------------------------------------------------------------------------
    Homework-002-5. sorunun bir çözümü
    (Not: Çözüm çalışma sorusunun verildiği tarihte işlenmiş konulara göre yazılmıştır)
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

fun main() = runPrintGoldBachTest()


fun runPrintGoldBachTest()
{
    while (true) {
        print("2'den büyük bir \"çift\" sayı giriniz")
        val a = readLine()!!.toInt()

        if (a == 0)
            break

        if (a < 3 || a % 2 != 0) {
             println("2'den büyükl bir \"çift\" sayı girmelisiniz!...")
            continue
        }

        printGoldBach(a)
    }

    println("Tekrar yapıyor musunuz?")
}


fun printGoldBach(a: Int)
{
    for (x in 2 until a) {
        val y = a - x

        if (isPrime(x.toLong()) && isPrime(y.toLong()) && x <= y)
            println("$x + $y = ${x + y} == $a")
    }
}

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
