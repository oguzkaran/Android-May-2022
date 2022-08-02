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


private fun IntArray.bubbleSortAscending()
{
    for (i in 0 until this.size - 1)
        for (k in 0 until this.size - i - 1)
            if (this[k + 1] < this[k])
                this.swap(k, k + 1)
}

private fun IntArray.bubbleSortDescending()
{
    for (i in 0 until this.size - 1)
        for (k in 0 until this.size - i - 1)
            if (this[k] < this[k + 1])
                this.swap(k, k + 1)
}

private fun IntArray.selectionSortAscending()
{
    var min: Int
    var minIndex: Int

    for (i in 0 until this.size - 1) {
        min = this[i]
        minIndex = i

        for (k in i + 1 until this.size)
            if (this[k] < min) {
                min = this[k]
                minIndex = k
            }
        this[minIndex] = this[i]
        this[i] = min
    }
}

private fun IntArray.selectionSortDescending()
{
    var max: Int
    var maxIndex: Int

    for (i in 0 until this.size - 1) {
        max = this[i]
        maxIndex = i

        for (k in i + 1 until this.size)
            if (max < this[k]) {
                max = this[k]
                maxIndex = k
            }
        this[maxIndex] = this[i]
        this[i] = max
    }
}


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


////////////////////////////////////////////////////////////////////

fun Array<IntArray>.sum() : Int
{
    var total = 0

    for (i in 0 until this.size )
        for(k in 0 until this[i].size)
            total += this[i][k]

    return total
}

fun IntArray.swap(a: Int, b: Int)
{
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}

fun CharArray.swap(a: Int, b: Int)
{
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}


fun IntArray.bubbleSort(descending: Boolean)
{
    if (descending)
        this.bubbleSortDescending()
    else
        this.bubbleSortAscending()
}

fun IntArray.selectionSort(descending: Boolean)
{
    if (descending)
        this.selectionSortDescending()
    else
        this.selectionSortAscending()
}

fun IntArray.bubbleSort() = this.bubbleSort(false)


fun IntArray.getHistogramData(n: Int) : IntArray
{
    val counts: IntArray = IntArray(n)

    for (value in this)
        counts[value]++

    return counts
}

fun IntArray.max() : Int
{
    var result = this[0]

    for (i in 1 until this.size)
        if (result < this[i])
            result = this[i]

    return result
}

fun Array<IntArray>.max() : Int
{
    var result = Integer.MIN_VALUE

    for (i in 0 until this.size )
        for (k in 0 until this[i].size )
            if(result < this[i][k])
                result = this[i][k]

    return result
}


fun IntArray.partition(threshold: Int) : Int
{
    var partitionIndex = 0

    while (partitionIndex != this.size && this[partitionIndex] < threshold)
        ++partitionIndex

    if (partitionIndex == this.size)
        return partitionIndex


    for (i in partitionIndex + 1 until  this.size)
        if(this[i] < threshold)
            this.swap(i, partitionIndex++)

    return partitionIndex
}



