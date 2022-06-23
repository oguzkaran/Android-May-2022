/*----------------------------------------------------------------------------------------------------------------------
    Taxi, Driver ve Client arasındaki ilişkiler
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

fun main()
{
    val driver = Driver(/*...*/)
    val taxi = Taxi(driver/*...*/)

    //...

    val client = Client(/*...*/)

    taxi.take(client)

    val client2 = Client(/*...*/)

    taxi.take(client2)

    //...
}

class Client {
    //...
}

class Driver {
    //...
}

class Taxi(var driver: Driver /*...*/) {
    //...

    fun take(c: Client)
    {
        //...
    }
}
