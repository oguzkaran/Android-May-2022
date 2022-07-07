/*----------------------------------------------------------------------
	FILE        : Complex.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 07.07.2022

	Complex class that represents a complex number in mathematics

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.math

import kotlin.math.sqrt

data class Complex(val real: Double = 0.0, val imag: Double = 0.0) {
    val norm : Double
        get() = sqrt(real * real + imag * imag)

    val length: Double
        get() = norm

    val conjugate: Complex
        get() = Complex(real, -imag)

    operator fun component3() = norm
    operator fun component4() = conjugate

    override fun toString() = "($real, $imag)"
    //...
}