/*----------------------------------------------------------------------------------------------------------------------
    AnalyticalCircle sınıfı ve test kodu
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.kotlin.util.math.geometry.AnalyticalCircle

fun main()
{
    val ac = AnalyticalCircle(-2.3, 23.5, 56.7)

    println("Radius:${ac.r}")
    println("Area:${ac.area}")
    println("Circumference:${ac.circumference}")
    println("Center:${ac.x}, ${ac.y}")

    ac.r = 2.3
    ac.x = 200.0
    ac.y = 12.3

    println("Radius:${ac.r}")
    println("Area:${ac.area}")
    println("Circumference:${ac.circumference}")
    println("Center:${ac.x}, ${ac.y}")
}
