/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte A nesnesi tarafından yaratılan B nesnesinin referansı A içindeki referansa
    atandığından artık A nesnesi ve B nesnesi garbage collected duruma gelemez. Bu durumda "bellek sızıntısı (memory leak)"
    oluşur
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

fun main()
{
    foo()

    //...
}

fun foo()
{
    var a = A()

    a.doWork()

    a = A()
    //...
}

class A {
    private var mB: B? = null

    inner class B {
        fun doSomething()
        {
            //...
        }
    }

    fun doWork()
    {
        mB = this.B()

        mB?.doSomething()
    }
}
