/*----------------------------------------------------------------------------------------------------------------------
    Iterable arayüzünün dropLast eklenti metodu
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.app.samples.loadNamesFromFile
import org.csystem.kotlin.util.console.readInt
import org.csystem.kotlin.util.console.readString

fun main()
{
    try {
        val text = readString("Bir yazı giriniz:")
        val count = readInt("Sorgudan son kaç tanesi atılsın:")
        val allNames = loadNamesFromFile("names.csv")
        val names = allNames.filter { it.contains(text, ignoreCase = true) }
                .drop(count)
                .map { it.lowercase() }
                .toList()

        allNames.filter { it.contains(text, ignoreCase = true) }.forEach(::println)
        println("---------------------------------------------------------")
        names.forEach(::println)
    }
    catch (ex: Throwable) {
        println(ex.message)
    }
}

