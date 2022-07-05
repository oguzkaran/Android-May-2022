/*----------------------------------------------------------------------
	FILE        : Circle.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 28.06.2022

	Circle class that represents the circle in geometry

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.math.geometry

import kotlin.math.abs

open class Circle(radius: Double = 0.0) {
    var radius: Double = abs(radius)
        set(value) {
            field = abs(value)
        }

    val area : Double
        get() = Math.PI * radius * radius

    val circumference : Double
        get() = 2 * Math.PI * radius

    override fun toString() = "r = $radius"
}