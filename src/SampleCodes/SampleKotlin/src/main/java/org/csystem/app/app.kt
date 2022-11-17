/*----------------------------------------------------------------------------------------------------------------------
    Bir thread başka bir thread join metodu ile bekleyebllir. join metodunun parametresiz overload'u thread sonlanana
    kadar beklemek amaçlı kullanılır. Parametreli overload'ları en fazla beklenmesi gereken süreyi alırlar
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import kotlin.concurrent.thread
import kotlin.random.Random

fun main()
{
    var sum = 0

    thread{ sum = sumRandomNumbersCallback()}
        .apply { join(); println("Sum:$sum")}
}

private fun sumRandomNumbersCallback() : Int
{
    var sum = 0

    for (i in 1..10) {
        val value = Random.nextInt(100)

        print("$value ")
        sum += value
        Thread.sleep(1000)
    }
    println()
    return sum
}
