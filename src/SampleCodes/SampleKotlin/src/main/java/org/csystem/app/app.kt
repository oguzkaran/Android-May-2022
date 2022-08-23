/*----------------------------------------------------------------------------------------------------------------------
    apply eklenti fonksiyonu: apply eklenti fonksiyonunun callback'ine this geçirilir
    "apply the following assignments or calls to the object"
    apply fonksiyonu çağrıldığı referansa geri döner
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.kotlin.util.console.readInt

class Sample {
    var a: Int = 0
    fun foo()
    {
        println("foo")
    }

    fun bar()
    {
        println("bar")
    }

    fun tar() : Int
    {
        println("tar")
        return  10;
    }

    override fun toString() = "Sample"
}

fun main()
{
    var x: Int

    val s = Sample().apply {
        a = readInt("Bir sayı giriniz:")
        this.foo()
        bar()
        println("Merhaba")
        x = tar()
    }

    println("s.a = ${s.a}")
    println("x = $x")
    println(s)
}

