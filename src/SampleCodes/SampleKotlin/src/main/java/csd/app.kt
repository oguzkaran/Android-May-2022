/*----------------------------------------------------------------------------------------------------------------------
    İki yazının aynı olup olmadığı == veya != operatörleri ile test edilebilir
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    print("Birinci yazıyı giriniz:")
    val s = readLine()!!

    print("İkinci yazıyı giriniz:")
    val k = readLine()!!

    println(if (s == k) "Aynı yazı" else "Farklı yazılar")
}