/*----------------------------------------------------------------------------------------------------------------------
     Kotlin'de Java'dan farklı olarak yakalanan bir değişkenin değeri scope'u içerisinde değiştirilebilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.kotlin.util.console.readInt

fun main()
{
    var a = readInt("Bir sayı giriniz:")

    val ix = object: IX {
        override fun foo()
        {
            println("a = $a")
            a++
        }
    }

    ix.foo()
    ix.foo()

}

interface IX {
    fun foo()
}
