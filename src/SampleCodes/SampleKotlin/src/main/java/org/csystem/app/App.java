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

    enum MyMonth {
        JAN(31, "OCAK"), FEB(28, "ŞUBAT"), MAR(31, "MART"), APR(30, "NİSAN"), MAY(31, "MAYIS"),  JUN(30, "HAZİRAN"),
        JUL(31, "TEMMUZ"), AUG(31, "AĞUSTOS"), SEP(30, "EYLÜL"), OCT(31, "EKİM"), NOV(30, "KASIM"), DEC(31, "ARALIK");

        public final int days;
        public final String textTR;
        MyMonth(int d, String t)
        {
            days = d;
            textTR = t;
        }
    }
}

