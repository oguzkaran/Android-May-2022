/*----------------------------------------------------------------------
	FILE        : Point.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 16.06.2022

	Immutable Point class that represents a 2 dimensional point in
	cartesian plane

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.math.geometry

class Point(val x: Double = 0.0, val y: Double = 0.0) {
    fun distance(other: Point) = distance(other.x, other.y)
    fun distance(a: Double = 0.0, b: Double = 0.0) = Math.sqrt(Math.pow(x - a, 2.0) + Math.pow(y - b, 2.0))
    //...
}