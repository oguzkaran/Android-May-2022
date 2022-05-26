/*----------------------------------------------------------------------------------------------------------------------
    Sınıf Çalışması: Parametresi ile aldığı bir sayının basamaklarının basamak sayıncı kuvvetleri toplamının
    kendisine eşit olup olmadığını test eden isArmstrong isimli fonksiyonu yazınız ve aşağıdaki kod ile test ediniz. Fonksiyon
    negatif değerler için false değerini döndürecektir

    Açıklama: Kuvvet alma işlemi için bir önceki örnekte yazılan pow fonksiyonu kullanılacaktır
----------------------------------------------------------------------------------------------------------------------*/
package csd

fun main()
{
    isArmstrongTest()
}

fun isArmstrongTest()
{
    for (a in 0..999999)
        if (isArmstrong(a))
            println(a)
}

fun isArmstrong(a: Int) : Boolean
{
    TODO("Write tjat function. This is your task")
}

