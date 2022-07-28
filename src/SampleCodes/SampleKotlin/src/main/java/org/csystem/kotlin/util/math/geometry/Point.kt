/*----------------------------------------------------------------------
	FILE        : Point.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 28.07.2022

	Immutable Point class that represents a 2 dimensional point in
	cartesian plane

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.math.geometry

import kotlin.math.sqrt

data class Point(val x: Double = 0.0, val y: Double = 0.0) {
    internal constructor(p: Point) : this(p.x, p.y)
    constructor(mutablePoint: MutablePoint) : this(mutablePoint.x, mutablePoint.y)

    fun distance(other: Point) = distance(other.x, other.y)
    fun distance(a: Double = 0.0, b: Double = 0.0) = sqrt((x - a) * (x - a) + (y - b) * (y - b))
    fun toMutablePoint() = MutablePoint(x, y)
    override fun toString() = "($x, $y)"
    //...
}