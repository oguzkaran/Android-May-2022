/*----------------------------------------------------------------------------------------------------------------------
    Thread sınıfının isInterrupted metodu interrupt flag değeri set edilmişse, reset duruma getirmez. Aşağıdaki örneği
    inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import kotlin.concurrent.thread
import kotlin.random.Random

fun main()
{
    val t = thread{ threadCallback() }

    Thread.sleep(Random.nextLong(1000, 4000))
    t.interrupt()
    Thread.sleep(Random.nextLong(1000, 4000))
    t.interrupt()
    println("Main ends!...")
}

fun threadCallback()
{
    var i = 0

    val self = Thread.currentThread();
    while (!self.isInterrupted) {
        println("First loop:${i++} ")
        //...
    }

    while (!Thread.interrupted()) {
        println("Second loop:${i++} ")
        //...
    }

    println("\nThread ends!...")
}
