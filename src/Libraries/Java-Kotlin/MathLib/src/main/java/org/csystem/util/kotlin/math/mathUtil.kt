/*----------------------------------------------------------------------
	FILE        : mathUtil.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 26.07.2022

	Utility functions for mathematical operations

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/

package org.csystem.util.kotlin.math

import kotlin.math.sqrt
import kotlin.random.Random


fun solveQuadraticEquation(a: Double, b: Double, c: Double) : Triple<Double, Double, Boolean>
{
    val delta = b * b - 4 * a * c

    return when {
        delta > 0 -> {val sqrtDelta = sqrt(delta); return Triple((-b + sqrtDelta) / (2 * a), (-b - sqrtDelta) / (2 * a), true)}
        delta == 0.0 -> Triple(-b / (2 * a), -b / (2 * a), true)
        else -> Triple(Double.NaN, Double.NaN, false)
    }
}


fun Random.randomComplex(min: Double = .0, bound: Double = 1.0) = Complex(this.nextDouble(min, bound), this.nextDouble(min, bound))
fun Random.randomComplex(min: Int = 0, bound: Int = 1) = randomComplex(min.toDouble(), bound.toDouble())


fun Random.randomMutableComplex(min: Double = .0, bound: Double = 1.0) = MutableComplex(this.nextDouble(min, bound), this.nextDouble(min, bound))
fun Random.randomMutableComplex(min: Int = 0, bound: Int = 1) = randomMutableComplex(min.toDouble(), bound.toDouble())
