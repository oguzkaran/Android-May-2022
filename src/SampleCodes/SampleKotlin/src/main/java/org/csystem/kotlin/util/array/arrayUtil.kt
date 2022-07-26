/*----------------------------------------------------------------------
	FILE        : arrayUtil.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 26.07.2022

	Utility functions for array operations

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/

package org.csystem.kotlin.util.array

import kotlin.random.Random

fun IntArray.write() = write(1)

fun IntArray.write(n: Int)
{
    val fmt = """%0${n}d """

    for (value in this)
        print(fmt.format(value))

    println()
}

fun DoubleArray.write()
{
    for (value in this)
        println("$value")
}

fun LongArray.write()
{
    for (value in this)
        println("$value")
}

fun IntArray.fillRandomArray(min: Int, max: Int, random: Random = Random)
{
    for (i in this.indices)
        this[i] = random.nextInt(min, max + 1)
}

fun DoubleArray.fillRandomArray(min: Double, max: Double, random: Random = Random)
{
    for (i in this.indices)
        this[i] = random.nextDouble(min, max + 1)
}


fun LongArray.fillRandomArray(min: Long, max: Long, random: Random = Random)
{
    for (i in this.indices)
        this[i] = random.nextLong(min, max + 1)
}

fun Random.randomArray(count: Int, min: Int, max: Int) : IntArray
{
    val a = IntArray(count)

    a.fillRandomArray(min, max, this)

    return a
}

fun Random.randomArray(count: Int, min: Double, max: Double) : DoubleArray
{
    val a = DoubleArray(count)

    a.fillRandomArray(min, max, this)

    return a
}

fun Random.randomArray(count: Int, min: Long, max: Long) : LongArray
{
    val a = LongArray(count)

    a.fillRandomArray(min, max, this);

    return a
}

fun IntArray.sum() : Int
{
    var total = 0

    for (value in this)
        total += value

    return total
}