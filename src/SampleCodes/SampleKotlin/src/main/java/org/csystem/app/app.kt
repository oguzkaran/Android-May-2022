/*----------------------------------------------------------------------------------------------------------------------
   Yıldızsız import bildiriminde ilgili isme takma isim (alias) verilebilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app

import test.foo as tFoo
import mest.foo as mFoo

fun main()
{
    tFoo()
    mFoo()
}