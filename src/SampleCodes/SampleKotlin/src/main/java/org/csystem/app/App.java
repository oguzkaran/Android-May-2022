/*----------------------------------------------------------------------------------------------------------------------
    T1 a;
    T2 b;
    a <op>= b -> a = (T1)(a op b)
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import java.util.Scanner;

class App {
    public static void main(String [] args)
    {
        Scanner kb = new Scanner(System.in);

        String s1 = kb.nextLine();
        String s2 = kb.nextLine();

        System.out.println(s1 == s2);
    }
}

