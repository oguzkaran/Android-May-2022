/*----------------------------------------------------------------------------------------------------------------------
     type aliases
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

typealias StringArrayList = ArrayList<String>

fun main()
{
    val sList = StringArrayList() //ArrayList<String>()

    sList.add("ankara")
    sList.add("istanbul")
    sList.add("izmir")

    for (city in sList)
        println(city)
}
