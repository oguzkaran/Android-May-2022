/*----------------------------------------------------------------------------------------------------------------------
    T1 a;
    T2 b;
    a <op>= b -> a = (T1)(a op b)
----------------------------------------------------------------------------------------------------------------------*/
package csd;

import java.util.Random;
import java.util.Scanner;

class App {
    public static void main(String [] args)
    {
        Random r = new Random();

        int a = r.nextInt(2, 13);

        System.out.printf("a = %d%n", a);

        System.out.println(switch (a) {
            case 7, 11 -> "Kazandı";
            case 2, 3, 12 -> "Kaybetti";
            default -> "Tekrar zarlar atılacak";
        });

    }
}

