/*----------------------------------------------------------------------------------------------------------------------
    Türemiş sınıf türünden (sub class) bir referans taban sınıf (super class) türünden bir referansa doğrudan (implicit)
    atanabilir (upcasting)
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

fun main()
{
    val x = B()
    var y: A = x

    //...
}

open class A {
    //...
}

class B : A() {
    //...
}
