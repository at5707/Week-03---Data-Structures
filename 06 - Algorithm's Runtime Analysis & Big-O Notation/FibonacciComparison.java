public class FibonacciComparison {
    public static int fibonacciRecursive(int n) {
        if (n<=1) {
            return n;
        }
        return fibonacciRecursive(n-1)+fibonacciRecursive(n-2);
    }
    public static int fibonacciIterative(int n) {
        if (n<=1) {
            return n;
        }
        int a=0,b=1,sum=0;
        for (int i = 2; i < n; i++) {
            sum=a+b;
            a=b;
            b=sum;
        }
        return b;
    }
    public static void testFibonacci(int n) {
        System.out.printf("\nFibonacci(%d):\n", n);
        if (n<=40) {
            long start=System.nanoTime();
            int r=fibonacciRecursive(n);
            long time=System.nanoTime()-start;
            System.out.printf("Recursive Result: %d in %.3f sec\n", r, time/1e9);
        } else {
            System.out.println("Recursive Result: Skipped (Too Slow)");
        }
        long start=System.nanoTime();
        int i=fibonacciIterative(n);
        long time=System.nanoTime()-start;
        System.out.printf("Iterative Result: %d in %.6f sec\n", i, time/1e9);
    }
    public static void main(String[] args) {
        System.out.println("Recursive vs Iterative Fibonacci Performance");
        int[] testValues={10,30,50};
        for (int n : testValues) {
            testFibonacci(n);
        }
    }
}
