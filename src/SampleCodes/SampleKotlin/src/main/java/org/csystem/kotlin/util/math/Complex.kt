/*----------------------------------------------------------------------
	FILE        : Complex.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 05.07.2022

	Complex class that represents a complex number in mathematics

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.math

import kotlin.math.sqrt

class Complex(val real: Double = 0.0, val imag: Double = 0.0) {
    val norm : Double
        get() = sqrt(real * real + imag * imag)

    val length: Double
        get() = norm

    val conjugate: Complex
        get() = Complex(real, -imag)

    override fun toString() = "($real, $imag)"
    //...
}