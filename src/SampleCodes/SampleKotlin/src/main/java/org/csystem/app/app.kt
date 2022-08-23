/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

fun main()
{
    val r = 1..10
    val iter = r.iterator()

    while (iter.hasNext())
        print("${iter.next()} ")

    println()

    for (v in r)
        print("$v ")

    println()
}
