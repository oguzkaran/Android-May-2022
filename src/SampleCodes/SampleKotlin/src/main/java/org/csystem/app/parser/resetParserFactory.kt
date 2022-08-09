package org.csystem.app.parser

import org.csystem.kotlin.util.string.getRandomText
import org.csystem.kotlin.util.string.getRandomTextTR
import kotlin.random.Random

fun createRandomSourceResetFactory(count: Int) : ISourceReset = when (Random.nextInt(2)){
    0 -> StringSource(Random.getRandomText(count, "abc123445xyz?:"))
    else -> CharArraySource(Random.getRandomText(count, "abc123445xyz?:"))
}