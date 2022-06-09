/*----------------------------------------------------------------------
	FILE        : Circle.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 09.06.2022

	Circle class that represents the circle in geometry

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package csd

class Circle(r: Double = 0.0) {
    var r: Double = Math.abs(r)
        set(value) {
            field = Math.abs(value)
        }

    val area : Double
        get() = Math.PI * r * r

    val circumference : Double
        get() = 2 * Math.PI * r
}