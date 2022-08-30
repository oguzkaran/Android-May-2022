package org.csystem.kotlin.util.iterable

fun <T> write(iterable: Iterable<T>, sep: Char = ' ', end: Char = '\n')
{
    iterable.forEach {print(it); print(sep)}
    print(end)
}