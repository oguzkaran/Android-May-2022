/*----------------------------------------------------------------------------------------------------------------------
    Yerel fonksiyonlar içerisinde kendisinden önce bildirilen yerel değişkenler kullanılabilir hatta değiştirilebilir
    Anahtar Notlar: Java' da yakalanan (capture) değişkenlere faaliyet alanları boyunca bir kez atama yapılabilir.
    Bu atamanın da yakalanmadan önce yapılması zorunludur (effectively final)
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    print("Bir sayı giriniz:")
    val a = readLine()!!.toInt()

    foo(a)
}

fun foo(a: Int)
{
    var x = a

    fun isEven() = x++ % 2 == 0

    if (isEven())
        println("Çift")
    else
        println("Tek")

    println(x)
}