/*----------------------------------------------------------------------------------------------------------------------
    Sceheduler sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.util.scheduler.Scheduler
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

fun main()
{
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss")
    val scheduler = Scheduler(1, TimeUnit.SECONDS)

    scheduler.schedule { print("${formatter.format(LocalDateTime.now())}\r") }
}
