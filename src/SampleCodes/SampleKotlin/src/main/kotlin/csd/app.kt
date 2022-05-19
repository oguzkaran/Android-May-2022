/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örneği inceleyiniz. bar metodunun parametresinin default argümanı olan add metodu default arguman ile
    çağrıldığında çağrılır
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    bar()
    println("--------------------------------")
    bar(10)
}

fun add(a: Int, b: Int): Int
{
    println("add");

    return a + b
}

fun bar(a: Int = add(10, 20)) = println("a=$a")