/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte interrupt flag değeri set edilmesine rağmen thread sonlanmauyacak biçimde yazılmıştır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.kotlin.util.console.readInt
import kotlin.concurrent.thread
import kotlin.random.Random

fun main()
{
    val count = readInt("Bir sayı giriniz:")
    val t = thread{ threadCallback(count) }

    Thread.sleep(Random.nextLong(5000, 15001))
    t.interrupt()
    println("Main ends!...")
}

fun threadCallback(count: Int)
{
    for (i in 1..count) {
        try {
            print("$i ")
            Thread.sleep(1000)
        }
        catch (ignore: InterruptedException) {
            println("Interrupt geldi. Ama umursamıyorum!...")
        }
    }
}
