/*----------------------------------------------------------------------
	FILE        : FractionException.kt
	AUTHOR      : Android-May-2022 Group
	LAST UPDATE : 28.07.2022

	FractionException class

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.kotlin.util.math

class FractionException(message: String, val status: FractionExceptionStatus) : RuntimeException(message) {
    override val message = "Message: ${super.message}, Status: $status"
}