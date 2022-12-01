/*----------------------------------------------------------------------------------------------------------------------
    Yukarıdaki problemin kuyruklu çözümü. Çözümde sayaçlı semaphore kullanıldığına dikkat ediniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import java.util.concurrent.Executors
import java.util.concurrent.Semaphore
import kotlin.random.Random

const val QUEUE_SIZE = 10

class ProducerConsumer {
    private var mQueue = IntArray(QUEUE_SIZE)
    private val mThreadPool = Executors.newFixedThreadPool(2)
    private val mProducerSemaphore =  Semaphore(QUEUE_SIZE, true)
    private val mConsumerSemaphore =  Semaphore(0)
    private var mHead = 0
    private var mTail = 0
    private fun producerThreadCallback()
    {
        var count = 0

        do {
            mProducerSemaphore.acquire(mQueue.size)
            mQueue[mTail++] = ++count
            mTail %= QUEUE_SIZE
            mConsumerSemaphore.release()
            Thread.sleep(Random.nextLong(0, 500))
        } while (count != 100)
    }

    private fun consumerThreadCallback()
    {
        do {
            mConsumerSemaphore.acquire()
            val value = mQueue[mHead++]
            mHead %= QUEUE_SIZE
            mProducerSemaphore.release(mQueue.size)
            print("$value ")
            Thread.sleep(Random.nextLong(0, 500))
        } while (value != 99)
    }

    fun run()
    {
        mThreadPool.execute{producerThreadCallback()}
        mThreadPool.execute{consumerThreadCallback()}
        mThreadPool.shutdown()
    }
}

fun main()
{
    val pc = ProducerConsumer();

    pc.run()
}
