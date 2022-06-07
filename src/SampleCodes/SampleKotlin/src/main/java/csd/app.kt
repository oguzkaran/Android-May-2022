/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki kodda tam uyumdan (exact match) dolayı error oluşmaz
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    val s = Sample();

    //...

}

class Sample {
    fun foo(a:Int = 0, b:Double = 4.5) = println("foo(Int, Double)")
    fun foo(s:Sample = Sample(), b:Int = 34) = println("foo(Sample, Int)")

}