/*----------------------------------------------------------------------
	FILE        : Value.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 11.08.2022

	Value class

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.tuple

import java.io.Serializable

data class Value<out T>(val value: T) : Serializable {
    fun toList() : List<T> = listOf(value)
}
