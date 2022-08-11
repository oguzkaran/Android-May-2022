/*----------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.kotlin.util.console.ConsoleKt;

class App {
    public static void main(String[] args)
    {
        Sample.run();
    }
}

class Sample {
    public static void run()
    {
        var a = ConsoleKt.readInt("Bir sayı giriniz", "");

        var ia = new IA() {
            @Override
            public void foo()
            {
                System.out.printf("a = %d%n", a);
            }
        };

        ia.foo();
        ia.foo();
    }
}


interface IA {
    void foo();

}

