/*----------------------------------------------------------------------
	FILE        : arrayUtil.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 14.07.2022

	Utility functions for array operations

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/

package org.csystem.kotlin.util.array

import kotlin.random.Random

fun write(a: IntArray)
{
    for (value in a)
        print("$value ")

    println()
}

fun write(a: DoubleArray)
{
    for (value in a)
        println("$value")
}

fun write(a: LongArray)
{
    for (value in a)
        println("$value")
}


fun randomIntArray(count: Int, min: Int, max: Int, random: Random = Random) : IntArray
{
    val a = IntArray(count)

    for (i in a.indices)
        a[i] = random.nextInt(min, max + 1)

    return a
}

fun randomDoubleArray(count: Int, min: Double, max: Double, random: Random = Random) : DoubleArray
{
    val a = DoubleArray(count)

    for (i in a.indices)
        a[i] = random.nextDouble(min, max)

    return a
}

fun randomLongArray(count: Int, min: Long, max: Long, random: Random = Random) : LongArray
{
    val a = LongArray(count)

    for (i in a.indices)
        a[i] = random.nextLong(min, max)

    return a
}


fun sum(a: IntArray) : Int
{
    var total = 0

    for (e in a)
        total += e

    return total
}