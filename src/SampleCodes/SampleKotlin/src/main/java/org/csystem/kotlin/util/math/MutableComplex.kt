/*----------------------------------------------------------------------
	FILE        : MutableComplex.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 28.07.2022

	MutableComplex class that represents a complex number in mathematics

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.math

import kotlin.math.sqrt

private fun plus(re1: Double, im1: Double, re2: Double, im2: Double) = MutableComplex(re1 + re2, im1 + im2)
private fun minus(re1: Double, im1: Double, re2: Double, im2: Double) = plus(re1, im1, -re2, -im2)

operator fun Double.plus(z: MutableComplex) = plus(this, 0.0, z.real, z.imag)
operator fun Double.minus(z: MutableComplex) = minus(this, 0.0, z.real, z.imag)
operator fun Double.times(z: MutableComplex) : MutableComplex = TODO()
operator fun Double.div(z: MutableComplex) : MutableComplex = TODO()

data class MutableComplex(var real: Double = 0.0, var imag: Double = 0.0) {
    val norm : Double
        get() = sqrt(real * real + imag * imag)

    val length: Double
        get() = norm

    val conjugate: MutableComplex
        get() = MutableComplex(real, -imag)

    operator fun get(i: Int) = when {
        i !in 0..1 -> throw IndexOutOfBoundsException("Illegal index")
        i == 0 -> real
        else -> imag
    }

    operator fun set(i: Int, value: Double) = when {
        i !in 0..1 -> throw IndexOutOfBoundsException("Illegal index")
        i == 0 -> real = value
        else -> imag = value
    }

    operator fun plus(other: MutableComplex) = plus(real, imag, other.real, other.imag)
    operator fun plus(value: Double) = plus(real, imag, value, 0.0)
    operator fun minus(other: MutableComplex) = minus(real, imag, other.real, other.imag)
    operator fun minus(value: Double) = minus(real, imag, value, 0.0)
    operator fun times(other: MutableComplex) : MutableComplex = TODO()
    operator fun times(value: Double) : MutableComplex = TODO()
    operator fun div(other: MutableComplex) : MutableComplex = TODO()
    operator fun div(value: Double) : MutableComplex = TODO()
    operator fun unaryMinus() = MutableComplex(-real, -imag)
    operator fun unaryPlus() = copy()

    operator fun inc() = MutableComplex(real + 1, imag)
    operator fun dec() = MutableComplex(real - 1, imag)

    operator fun invoke(real: Double, imag: Double)
    {
        this.real = real
        this.imag = imag
    }

    operator fun component3() = norm
    operator fun component4() = conjugate

    override fun toString() = "($real, $imag)"
    //...
}