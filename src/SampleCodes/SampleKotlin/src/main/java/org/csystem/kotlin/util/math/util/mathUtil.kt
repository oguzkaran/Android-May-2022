package org.csystem.kotlin.util.math.util

import kotlin.math.sqrt


data class SecondOrderEqRootInfo(val x1: Double, val x2: Double = x1)


fun findSecondOrderEqRoots(a: Double, b: Double, c: Double) : SecondOrderEqRootInfo
{
    val delta = b * b - 4 * a * c

    return when {
        delta > 0 -> {val sqrtDelta = sqrt(delta); return SecondOrderEqRootInfo((-b + sqrtDelta) / (2 * a), (-b - sqrtDelta) / (2 * a))}
        delta == 0.0 -> SecondOrderEqRootInfo(-b / (2 * a))
        else -> SecondOrderEqRootInfo(Double.NaN)
    }
}
