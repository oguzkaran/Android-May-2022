/*----------------------------------------------------------------------------------------------------------------------
    Generic sınıflar için nesne yaratılırken generic parametrelerin türleri tespit edilemezse açılım kesin kullanılmalıdır.
    Aksi durumda error oluşur
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

fun main()
{
    val s1 = Sample<Int>()
    val s2 = Sample<Double>()

    s1.foo(10)
    s2.foo(10.7)
}

class Sample<T> {
    //...
    fun foo(t: T) = println(t)
}