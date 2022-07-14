package org.csystem.app.game.ballfall

import org.csystem.game.ballfall.runBallFall

fun runBallFallGameApp()
{
    while (true) {
        print("Genişliği giriniz:")
        val width = readLine()!!.toInt()

        if (width <= 0)
            break

        print("Yüksekliği giriniz:")
        val height = readLine()!!.toInt()

        print(runBallFall(width, height))
    }

    println("Tekrar yapıyor musunuz?")
}
