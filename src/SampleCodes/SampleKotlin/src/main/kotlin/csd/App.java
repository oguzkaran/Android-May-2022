package csd;

class App {
    public static void main(String[] args)
    {
        long a = 4_000_000_000L;

        System.out.println(a);
    }
}

class Sample {
    public void foo(int a, int b)
    {
        int numberOfStudents;
    }

    public void bar(int a, int b)
    {
        int numberOfStudents;
    }

    public static int digitsSum(int a)
    {
        int sum = 0;

        while (a != 0) {
            sum += a % 10;
            a /= 10;
        }

        return sum;
    }


    public static void foo(int a)
    {
        a = 10;


    }
}
