/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main()
{
    val f = suspend {
        val job = GlobalScope.launch {
            for (i in 1..10) {
                print("$i ")
                delay(1000)
            }
            println()
        }

        job.join()

        print("end!...")
        //...
    }

    doWork(f)

    //...
}

fun doWork(block: suspend () -> Unit)
{
    //...
    runBlocking { block() }
    //...
}
