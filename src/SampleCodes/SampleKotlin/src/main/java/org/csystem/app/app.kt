/*----------------------------------------------------------------------------------------------------------------------
    Sınıf Çalışması: Klavyeden katsayıları girilen ikinci dereceden bir denklemin köklerini bulan programı yazınız
    Aşağıdaki örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package csd

import org.csystem.kotlin.util.math.util.findSecondOrderEqRoots

private data class Coefficients (val a: Double, val b: Double, val c: Double)

private fun getCoefficientsFromStdIn(aMsg: String, bMsg: String, cMsg: String) : Coefficients
{
    print(aMsg)
    val a = readLine()!!.toDouble()

    print(bMsg)
    val b = readLine()!!.toDouble()

    print(cMsg)
    val c = readLine()!!.toDouble()

    return Coefficients(a, b, c)
}

private fun run()
{
    val (a, b, c) = getCoefficientsFromStdIn("a?", "b?", "c?")
    val (x1, x2) = findSecondOrderEqRoots(a, b, c)

    doWorkForRoots(x1, x2)

    println("Tekrar yapıyor musunuz?")
}

private fun doWorkForRoots(x1: Double, x2: Double)
{
    println(if (!x1.isNaN()) "x1 = $x1, x2 = $x2" else "Gerçek kök yok")
}

fun main() = run()



