/*----------------------------------------------------------------------------------------------------------------------
    if ifadesinin koşul operatörü yerine kullanımı
    Anahtar Notlar: Kotlin'de koşul operatörü (conditional operator) yoktur
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    print("Bir sayı giriniz:")
    val a = readLine()!!.toInt()

    println(if (a % 2 == 0) "Çift" else "Tek")
}