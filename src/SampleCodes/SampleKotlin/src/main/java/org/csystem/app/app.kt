/*----------------------------------------------------------------------------------------------------------------------
    Set tarzı collection'larda "hash kullananlar eşitlik kontrolü için equals ve hasCode metotlarının geri dönüş
    değerine bakarlar. Bu durumda programcı bir tür durumlar için bu metotları gerekirse override etmelidir
    Anahtar Notlar: Hash code üretmek ayrı bir kavramdır ve bir çok durumda üretmenin farklı yöntemleri vardır. Aşağıdaki
    örnekte ürünün id değeri Int olduğundan ve aynı id'ye sahip birden fazla ürünün "set" içerisinde bulunmaması gerektiği
    varsayımıyla düşünülmüştür
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import org.csystem.app.samples.loadProductsFromFileAsIterable
import org.csystem.app.samples.loadProductsFromFileAsSet

fun main()
{
    try {
        val allProducts = loadProductsFromFileAsIterable("products.csv")
        val products = loadProductsFromFileAsSet("products.csv")

        println(products.javaClass.name)

        products.forEach(::println)
        println("Count:${products.count()}")
        println("All products Count:${allProducts.count()}")
    }
    catch (ex: Throwable) {
        ex.printStackTrace()
    }
}