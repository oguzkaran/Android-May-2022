/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte birden fazla formatter ile işlem yapan örnek bir fonksiyon yazılmıştır. Detaylar gözardı edilmiştir.
    Bir kütüphane içerisine daha detaylısı eklenecektir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.util.kotlin.math.solveQuadraticEquation

fun main()
{
    val (x1, x2) = solveQuadraticEquation(1.0, -3.0, -18.0)

    println("x1 = $x1, x2 = $x2")
}