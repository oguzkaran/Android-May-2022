/*----------------------------------------------------------------------------------------------------------------------
    Collections sınıfının synchronizedXXX metotları collection'larıu sarmalayan "thread safe" collection nesnesi yaratmak
    için kullanılır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.kotlin.util.console.readInt
import java.util.*
import kotlin.concurrent.thread

fun main()
{
    val nThreads = readInt("Input thread count:")
    val count = readInt("Input count:")
    val generator = IntListGenerator(count)
    val threads = ArrayList<Thread>(nThreads)

    for (i in 1..nThreads)
        threads.add(thread{generator.threadCallback()})

    threads.forEach{it.join()}
    println("Size:${generator.size}")
}

class IntListGenerator(private val mCount: Int) {
    private val mList: MutableList<Int> = Collections.synchronizedList(ArrayList())
    val size: Int
        get() = mList.size

    fun threadCallback()
    {
        for (i in 1..mCount)
            mList.add(i)
    }
}
