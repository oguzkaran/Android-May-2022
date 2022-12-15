package org.csystem.android.app.hiltdi.data

interface IMapper<T1, T2> {
    fun toType1(t: T2) : T1
    fun toType2(t: T1) : T2
}