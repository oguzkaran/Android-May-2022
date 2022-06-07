/*----------------------------------------------------------------------------------------------------------------------
    Sınıfın primary ctor'u varsa tüm secondary ctor'ların doğrudan ya da dolaylı olarak bu ctor'u çağırıyor olmaları
    gerekir. Bu işlem :this ctor sentaksı yapılır
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    var s = Sample(3)
    println("------------------------")
    var k = Sample(3.toShort())

    //...
}

class Sample(var a: Double) {
    init {
        println("primary ctor")
    }

    constructor(a: Int) : this(a.toDouble())
    {
        println("secondary ctor with parameter Int")
    }

    constructor(a: Short) : this(a.toInt())
    {
        println("secondary ctor with parameter Short")
    }
}
