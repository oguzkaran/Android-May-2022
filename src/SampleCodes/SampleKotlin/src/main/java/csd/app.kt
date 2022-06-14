/*----------------------------------------------------------------------------------------------------------------------
    String sınıfının tüm karakterleri for döngüsü ile elde edilebilir. Yani String sınıfı "iterable"'dır. Iterable kavramı
    ileride ele alınacaktır
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    print("Bir yazı giriniz:")
    val s = readLine()!!

    for (ch in s)
        print("${ch} ")

    println()
}
