/*----------------------------------------------------------------------------------------------------------------------
    T1 a;
    T2 b;
    a <op>= b -> a = (T1)(a op b)
----------------------------------------------------------------------------------------------------------------------*/
package csd;

class App {
    public static void main(String [] args)
    {

    }
}

class MyPoint {
    public double x;
    public double y;

    public MyPoint()
    {
    }

    public MyPoint(double a)
    {
        this(a, 0);
    }

    public MyPoint(double a, double b)
    {
        x = a;
        y = b;
    }
}