/*----------------------------------------------------------------------------------------------------------------------
    T1 a;
    T2 b;
    a <op>= b -> a = (T1)(a op b)
----------------------------------------------------------------------------------------------------------------------*/
package csd;

import java.util.Scanner;

class App {
    public static void main(String[] args)
    {
        Scanner kb = new Scanner(System.in);

        System.out.print("Bir sayı giriniz:");
        int a = Integer.parseInt(kb.nextLine());


        System.out.println(a % 2 == 0 ? "Çift" : "Tek");
    }
}

