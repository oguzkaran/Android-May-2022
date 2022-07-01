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

        if (a is String) {
            val str: String = a as String
            println("$str -> ${str.uppercase()}")
        }
        else if (a is Int) {
            val value = a as Int
            println("$value * $value = ${value * value}")
        }
        else if (a is Double) {
            val value = a as Double
            println("$value")
        }
        else if (a is IntArray) {
            val array: IntArray = a as IntArray
            write(array)
            println("Toplam: ${sum(array)}")
        }
        else {
            val ch = a as Char
            println(ch)
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