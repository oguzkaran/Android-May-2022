/*----------------------------------------------------------------------------------------------------------------------
    when ifadesinin in ve !in (not in) operatörleri ile kullanımı. in ve !in operatörleri ileride ele alınacaktır
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    print("Bir sayı giriniz:")
    val num = readLine()!!.toInt()

    when (num) {
        in 10..20 -> println("10 <= num <= 20")
        !in 1..3 -> println("num < 1 || num > 3")
        else -> println("Geçersiz değer")
    }
}