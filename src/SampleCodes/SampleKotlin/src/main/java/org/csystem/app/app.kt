/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnek Kotlin'de geçersizdir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

open class A {
    open fun foo()
    {
        println("A.foo")
    }
    //...
}

open class B : A() {
    final override fun foo()
    {
        println("B.foo")
    }
}

open class C : B() {
    open fun foo() //error
    {
        //...
    }
}
