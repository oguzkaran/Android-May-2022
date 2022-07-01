package org.csystem.random

import org.csystem.kotlin.util.array.randomIntArray
import org.csystem.kotlin.util.string.getRandomTextTR
import kotlin.random.Random

//String, Int, Double, IntArray, Char
private fun getRandomObject(random: Random) : Any = when (random.nextInt(5)) {
        0 -> getRandomTextTR(random.nextInt(5, 15), random)
        1 -> random.nextInt(100)
        2 -> random.nextDouble()
        3 -> randomIntArray(random.nextInt(10, 21), 0, 99, random)
        else -> (if (random.nextBoolean()) 'A' else 'a') + random.nextInt(26)
    }


fun getRandomObjects(n: Int, random: Random = Random) = Array(n) { getRandomObject(random) }