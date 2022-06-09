/*----------------------------------------------------------------------------------------------------------------------
    Circle sınıfı ve test kodu
    (Daha profesyonel versiyon)
    Aşağıdaki örnekte area ve circumference property elemanları için backing field yaratılmaz
    Not: Circle sınıfı ileride göreceğimiz konular ile daha profesyonel yazılacaktır
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    val  c = Circle(-3.4)

    println(c.area)
    println(c.circumference)
    println("------------------------------")

    c.r = -4.4

    println(c.area)
    println(c.circumference)
    println("------------------------------")
}

class Circle(radius: Double = 0.0) {
    var r: Double = Math.abs(radius)
        set(value) {
            field = Math.abs(value)
        }

    val area: Double
        get() = Math.PI * r * r

    val circumference : Double
        get() = 2 * Math.PI * r
}
