/*----------------------------------------------------------------------------------------------------------------------
    Kotlin'de nokta içeren bir sabitin noktadan önceki kısmı (tam kısmı) sıfır ise sıfır yazılmayabilir.
    Ancak noktadan sonraki kısmı (ondalık kısmı) sıfır ise noktadan sonra bir şey yazmamak geçersizdir
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    var a = .3
    var b = 4. //error
}
