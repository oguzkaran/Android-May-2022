/*----------------------------------------------------------------------------------------------------------------------
    Complex sınıfının operatör fonksiyonları
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.kotlin.util.math.util.randomComplex
import kotlin.random.Random

fun main()
{
    val z = Random.randomComplex(-10, 10)

    println(z)
    println(-z)
    println(+z)
}
