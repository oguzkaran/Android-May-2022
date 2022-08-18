/*----------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.kotlin.util.console.readInt
import org.csystem.kotlin.util.numeric.isPrime

fun main()
{
    val a = IntArray(readInt("Bir sayı giriniz:")) {it + 1}
    val b = a.filter {it % 2 == 0}.toIntArray()

    a.forEach {print("$it ")}
    println()
    b.forEach {print("$it ")}

    println()

    val c = a.filter{it.isPrime()}

    c.forEach {print("$it ")}

    println()
}

