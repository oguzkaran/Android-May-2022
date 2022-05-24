/*----------------------------------------------------------------------------------------------------------------------
    == ve != operatörleeri. Bu operatörlerin fonksiyon karşılıkları ileride ele alınacaktır
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    print("Birinci sayıyı giriniz:")
    val a = readLine()!!.toInt()

    print("İkinci sayıyı giriniz:")
    val b = readLine()!!.toInt()
    val resEquals = a == b
    val resNotEquals = a != b

    println("resEquals = $resEquals, resNotEquals = $resNotEquals")
}