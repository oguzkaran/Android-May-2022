/*----------------------------------------------------------------------------------------------------------------------
    Thread sınıfını kullanarak thread yaratmak için Thread sınıfının Runnable parametreli ctor'ları kullanılabilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import kotlin.random.Random

fun main()
{
    val t = Thread(RandomGeneratorThread())

    t.start()
    println("main ends")
}

class RandomGeneratorThread : Runnable {
    private fun runCallback()
    {
        val value = Random.nextInt(100)

        print("%02d ".format(value))
        Thread.sleep(1000)
    }
    override fun run()
    {
        (1..10).forEach{runCallback()}
        println()
    }
}