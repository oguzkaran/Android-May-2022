/*----------------------------------------------------------------------
	FILE        : MutableFraction.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 28.07.2022

	MutableFraction class

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.math

operator fun Int.plus(other: MutableFraction) : MutableFraction = TODO()
operator fun Int.minus(other: MutableFraction) : MutableFraction = TODO()
operator fun Int.times(other: MutableFraction) : MutableFraction = TODO()
operator fun Int.div(other: MutableFraction) : MutableFraction = TODO()

class MutableFraction {
    var numerator = 0
    var denominator = 0
    val realValue: Double
        get() = TODO()

    operator fun plus(other: MutableFraction) : MutableFraction = TODO()
    operator fun plus(value: Int) : MutableFraction = TODO()

    operator fun minus(other: MutableFraction) : MutableFraction = TODO()
    operator fun minus(value: Int) : MutableFraction = TODO()

    operator fun times(other: MutableFraction) : MutableFraction = TODO()
    operator fun times(value: Int) : MutableFraction = TODO()

    operator fun div(other: MutableFraction) : MutableFraction = TODO()
    operator fun div(value: Int) : MutableFraction = TODO()

    operator fun unaryMinus() : MutableFraction = TODO()
    operator fun unaryPlus() : MutableFraction = TODO()

    operator fun inc() : MutableFraction = TODO()
    operator fun dec() : MutableFraction = TODO()

    operator fun compareTo(other: MutableFraction) : Int = TODO()
    operator fun compareTo(value: Int) : Int = TODO()

    operator fun set(i: Int, value: Int) : MutableFraction = TODO()
    operator fun get(i: Int) : Int = TODO()

    override fun toString(): String = TODO()
}