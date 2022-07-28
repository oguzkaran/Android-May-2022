/*----------------------------------------------------------------------
	FILE        : Complex.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 28.07.2022

	Immutable Complex class that represents a complex number in mathematics

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.math

import kotlin.math.sqrt

private fun plus(re1: Double, im1: Double, re2: Double, im2: Double) = Complex(re1 + re2, im1 + im2)
private fun minus(re1: Double, im1: Double, re2: Double, im2: Double) = plus(re1, im1, -re2, -im2)

operator fun Double.plus(z: Complex) = plus(this, 0.0, z.real, z.imag)
operator fun Double.minus(z: Complex) = minus(this, 0.0, z.real, z.imag)
operator fun Double.times(z: Complex) : Complex = TODO()
operator fun Double.div(z: Complex) : Complex = TODO()

data class Complex(val real: Double = 0.0, val imag: Double = 0.0) {
    val norm : Double
        get() = sqrt(real * real + imag * imag)

    val length: Double
        get() = norm

    val conjugate: Complex
        get() = Complex(real, -imag)

    operator fun get(i: Int) = when {
        i !in 0..1 -> throw IndexOutOfBoundsException("Illegal index")
        i == 0 -> real
        else -> imag
    }

    operator fun plus(other: Complex) = plus(real, imag, other.real, other.imag)
    operator fun plus(value: Double) = plus(real, imag, value, 0.0)
    operator fun minus(other: Complex) = minus(real, imag, other.real, other.imag)
    operator fun minus(value: Double) = minus(real, imag, value, 0.0)
    operator fun times(other: Complex) : Complex = TODO()
    operator fun times(value: Double) : Complex = TODO()
    operator fun div(other: Complex) : Complex = TODO()
    operator fun div(value: Double) : Complex = TODO()
    operator fun unaryMinus() = Complex(-real, -imag)
    operator fun unaryPlus() = copy()

    operator fun component3() = norm
    operator fun component4() = conjugate

    override fun toString() = "($real, $imag)"
    //...
}