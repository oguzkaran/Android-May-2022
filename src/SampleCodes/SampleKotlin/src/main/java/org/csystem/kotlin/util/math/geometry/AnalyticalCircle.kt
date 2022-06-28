/*----------------------------------------------------------------------
	FILE        : AnalyticalCircle.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 28.06.2022

	Circle class that represents the circle in cartesian coordinates

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.math.geometry

open class AnalyticalCircle(radius: Double = 0.0, x: Double = 0.0, y: Double = 0.0) : Circle(radius) {
    private val mCenter = MutablePoint(x, y)

    constructor(radius: Double = 0.0, center: Point = Point()) : this(radius, center.x, center.y)

    var x: Double
        get() = mCenter.x
        set(value) {mCenter.x = value}

    var y: Double
        get() = mCenter.y
        set(value) {mCenter.y = value}

    var center: Point
        get() = mCenter.toPoint()
        set(value) {setCenter(value.x, value.y)}

    fun setCenter(x: Double, y: Double)
    {
        this.x = x
        this.y = y
    }

    fun offset(dx: Double, dy: Double = dx) = mCenter.offset(dx, dy)

    fun centerDistance(other: AnalyticalCircle) = mCenter.distance(other.mCenter)
}