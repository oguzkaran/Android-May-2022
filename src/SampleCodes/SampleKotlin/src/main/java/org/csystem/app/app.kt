/*----------------------------------------------------------------------------------------------------------------------
    Basit bir exception kullanımı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.kotlin.util.console.readDouble
import org.csystem.kotlin.util.console.readString

fun main()
{
    val s = readString("true veya false giriniz:")
    val b: Boolean = s.toBoolean()

    println(b)
}

