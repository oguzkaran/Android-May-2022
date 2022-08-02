/*----------------------------------------------------------------------
	FILE        : MutableFraction.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 02.08.2022

	MutableFraction class

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.math

import org.csystem.kotlin.util.numeric.gcd
import java.lang.IllegalArgumentException
import kotlin.math.abs

private fun plus(a1: Int, b1: Int, a2: Int, b2: Int) = MutableFraction(a1 * b2 + a2 * b1, b1 * b2)
private fun minus(a1: Int, b1: Int, a2: Int, b2: Int) = plus(a1, b1, -a2, b2)
private fun times(a1: Int, b1: Int, a2: Int, b2: Int) = MutableFraction(a1 * a2, b1 * b2)
private fun div(a1: Int, b1: Int, a2: Int, b2: Int) = times(a1, b1, b2, a2)

operator fun Int.plus(other: MutableFraction) = plus(this, 1, other.numerator, other.denominator)
operator fun Int.minus(other: MutableFraction) = minus(this, 1, other.numerator, other.denominator)
operator fun Int.times(other: MutableFraction) = times(this, 1, other.numerator, other.denominator)
operator fun Int.div(other: MutableFraction) = div(this, 1, other.numerator, other.denominator)

class MutableFraction(numerator: Int = 0, denominator: Int = 1) {
    private var mSimplify: Boolean = true

    private fun check(a: Int, b: Int)
    {
        if (b == 0) {
            if (a == 0)
                throw FractionException("Indeterminate", FractionExceptionStatus.INDETERMINATE)

            throw FractionException("Undefined", FractionExceptionStatus.UNDEFINED)
        }
    }

    private fun setFraction(a: Int, b: Int)
    {
        mSimplify = false
        if (a == 0) {
            numerator = 0
            denominator = 1
            return
        }
        var num = a
        var denom = b

        if (denom < 0) {
            num *= -1
            denom *= -1
        }

        val gcdValue = abs(num) gcd denom

        numerator = num / gcdValue
        denominator = denom / gcdValue
        mSimplify = true
    }

    var numerator: Int = numerator
        set(value)
        {
            field = value

            if (field == 0) {
                mSimplify = false
                denominator = 1
                mSimplify = true
                return
            }

            if (mSimplify) {
                val gcdVal = field gcd denominator
                field /= gcdVal
                mSimplify = false
                denominator /= gcdVal
                mSimplify = true
            }
        }
    var denominator: Int = denominator
        set(value)
        {
            check(numerator, value)
            field = value
            if (field < 0) {
                field *= -1
                mSimplify = false
                numerator *= -1
                mSimplify = true
            }

            if (mSimplify) {
                val gcdVal = field gcd numerator
                field /= gcdVal
                mSimplify = false
                numerator /= gcdVal
                mSimplify = true
            }
        }
    init {
        check(numerator, denominator)
        setFraction(numerator, denominator)
    }

    val realValue: Double
        get() = numerator.toDouble() / denominator

    operator fun plus(other: MutableFraction) = plus(numerator, denominator, other.numerator, other.denominator)
    operator fun plus(value: Int) = plus(numerator, denominator, value, 1)

    operator fun minus(other: MutableFraction) = minus(numerator, denominator, other.numerator, other.denominator)
    operator fun minus(value: Int) = minus(numerator, denominator, value, 1)

    operator fun times(other: MutableFraction) = times(numerator, denominator, other.numerator, other.denominator)
    operator fun times(value: Int) = times(numerator, denominator, value, 1)

    operator fun div(other: MutableFraction) = div(numerator, denominator, other.numerator, other.denominator)
    operator fun div(value: Int) = div(numerator, denominator, value, 1)

    operator fun unaryPlus() = MutableFraction(numerator, denominator)
    operator fun unaryMinus() = MutableFraction(-numerator, denominator)

    operator fun inc() = MutableFraction(numerator + denominator, denominator)

    operator fun dec() = MutableFraction(numerator - denominator, denominator)

    operator fun compareTo(other: MutableFraction) = numerator * other.denominator - denominator * other.numerator
    operator fun compareTo(value: Int) : Int = numerator - denominator * value
    operator fun set(i: Int, value: Int) = when (i) {
        0 -> numerator = value
        1 -> denominator = value
        else -> throw IllegalArgumentException("Invalid index")
    }

    operator fun get(i: Int) = when (i) {
        0 -> numerator
        1 -> denominator
        else -> throw IllegalArgumentException("Invalid index")
    }

    override operator fun equals(other: Any?) = other is MutableFraction && compareTo(other) == 0

    operator fun component1() = numerator
    operator fun component2() = denominator
    operator fun component3() = realValue
    override fun toString(): String = "%d%s".format(numerator, if (denominator == 1) "" else " / $denominator = $realValue")
}