package org.csystem.kotlin.util.tuple

sealed class SealedValue<T>(val t: T)

class MySealedValue(var a: Int) : SealedValue<Int>(a) {
    //..
}
