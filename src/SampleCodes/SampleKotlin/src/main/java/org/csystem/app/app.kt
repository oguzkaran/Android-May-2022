/*----------------------------------------------------------------------------------------------------------------------
    Lambda fonksiyonların bir kullanımı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import kotlin.random.Random

fun main()
{
    val a = IntArray(10)

    for (i in 0 until 10)
        a[i] = Random.nextInt(10, 20)

    forEach(a) {print("$it ")}
    println()
    transform(a) {it * it}
    forEach(a) {print("$it ")}
    println()
}

fun forEach(a: IntArray, f: (Int) -> Unit)
{
    for (elem in a)
        f(elem)
}

fun transform(a: IntArray, f: (Int) -> Int)
{
    for (i in a.indices)
        a[i] = f(a[i])
}
