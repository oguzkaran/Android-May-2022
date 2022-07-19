package org.csystem.app.randomanygenerator

import org.csystem.kotlin.util.array.sum
import org.csystem.kotlin.util.array.write
import org.csystem.kotlin.util.console.readInt
import org.csystem.random.getRandomObjects

private fun doWorkForObjects(count: Int)
{
    for (a in getRandomObjects(count)) {
        println("-----------------------------------------")
        println("Type: ${a.javaClass.name}")

        when (a) {
            is String -> println("$a -> ${a.uppercase()}")
            is Int -> println("$a * $a = ${a * a}")
            is Double -> println("$a")
            is IntArray -> {write(a); println("Toplam: ${sum(a)}")}
            else -> println(a as Char)
        }

        println("-----------------------------------------")
    }
}


fun runRandomAnyGeneratorApp()
{
    while (true) {
        val count = readInt("Bir sayı giriniz:")

        if (count <= 0)
            break;

        doWorkForObjects(count)
    }

    println("Tekrar yapıyor musunuz?")
}