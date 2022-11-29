/*----------------------------------------------------------------------------------------------------------------------
    ExecutorService arayüzünün submit metodu Future<V> arayüz referansı döndürür. Future<V> arayüzünün get metodu ile
    thread beklenebilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.kotlin.util.console.readInt
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import kotlin.random.Random

typealias AnyFutureList = ArrayList<Future<*>>

fun main()
{
    val count = readInt("Input number of threads to run:")
    val threadPool = Executors.newCachedThreadPool()
    val futures = AnyFutureList()

    for (i in 1..count)
        threadPool.submit{ threadCallback(10, "t$i") }.apply{ futures.add(this) }

    println("Waiting for all threads!...")
    for (future in futures)
        future.get(1, TimeUnit.SECONDS)

    println("main ends!...")
    threadPool.shutdown()
}

fun threadCallback(count: Int, name: String)
{
    for (i in 1..count) {
        println("$name->${Random.nextInt(100)}")
        Thread.sleep(1000)
    }
}

