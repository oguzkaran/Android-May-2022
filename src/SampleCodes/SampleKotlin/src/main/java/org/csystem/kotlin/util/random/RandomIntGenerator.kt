/*----------------------------------------------------------------------
	FILE        : RandomIntGenerator.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 30.08.2022

	Iterable RandomIntGenerator class

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.random

import kotlin.random.Random

class RandomIntGenerator(val n: Int, val min: Int, val max: Int, val random: Random = Random) : Iterable<Int> {
    private class RandomIntGeneratorIterator(val randomIntGenerator: RandomIntGenerator) : Iterator<Int> {
        var count = -1

        override fun hasNext(): Boolean
        {
            return  count + 1 < randomIntGenerator.n
        }

        override fun next(): Int
        {
            if (!hasNext())
                throw NoSuchElementException("No such random data")

            ++count
            return randomIntGenerator.random.nextInt(randomIntGenerator.min, randomIntGenerator.max)
        }

    }
    override fun iterator(): Iterator<Int>
    {
        return RandomIntGeneratorIterator(this)
    }
}