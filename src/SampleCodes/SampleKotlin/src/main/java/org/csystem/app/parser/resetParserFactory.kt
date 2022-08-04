package org.csystem.app.parser

import org.csystem.kotlin.util.string.getRandomTextTR
import kotlin.random.Random

fun createRandomSourceResetFactory(count: Int) : ISourceReset = when (Random.nextInt(2)){
    0 -> StringSource(Random.getRandomTextTR(count))
    else -> CharArraySource(Random.getRandomTextTR(count))
}