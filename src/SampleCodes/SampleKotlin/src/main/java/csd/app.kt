/*----------------------------------------------------------------------------------------------------------------------
    Random sınıfının nextBoolean metodu
----------------------------------------------------------------------------------------------------------------------*/
package csd

import kotlin.random.Random

fun main()
{
    for (i in 1..10)
        println(Random.nextBoolean())
}