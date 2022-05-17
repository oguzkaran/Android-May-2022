/*----------------------------------------------------------------------------------------------------------------------
    Bir fonksiyonun geri dönüş değeri fonksiyon bildiriminde gövde yazılmadan : den sonra yazılmalıdır. Unit
    C# ve Java'daki void anahtar sözcüğüne karşılık getirilebilir. Kotlin 1.1 versiyonundan sonra geri dönüş değeri olmayan
    fonskiyonlar için Unit yazılması zorunlu değildir
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main() : Unit
{
    println("Hello, World")
    foo()
    println("Goodbye, World")
}

fun foo()
{
    println("foo")
    bar()
}


fun bar() : Unit
{
    println("bar")
}


