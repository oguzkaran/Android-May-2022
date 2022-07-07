/*----------------------------------------------------------------------
	FILE        : MutablePoint.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 07.07.2022

	MutablePoint class that represents a 2 dimensional point in
	cartesian plane

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.math.geometry

data class MutablePoint(var x: Double = 0.0, var y: Double = 0.0) {
    constructor(point: Point) : this(point.x, point.y)
    fun distance(other: MutablePoint) = distance(other.x, other.y)
    fun distance(a: Double = 0.0, b: Double = 0.0) = Math.sqrt(Math.pow(x - a, 2.0) + Math.pow(y - b, 2.0))

    fun offset(dx: Double, dy: Double = dx)
    {
        x += dx
        y += dy
    }

    fun toPoint() = Point(x, y)
    //...
}