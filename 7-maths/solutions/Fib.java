class Fib {

    static int fib(int n) {
	if (n <= 1) return n;
	return fib(n - 1) + fib(n - 2);
    }

    static long fib2(long n) {
	if (n <= 1) return n;
	long f2 = 0, f1 = 1;

	for (long i = 2; i <= n; i++) {
	    long prev_f1 = f1;
	    f1 += f2;
	    f2 = prev_f1;
	}
	return f1;
    }

    public static void main(String[] args) {
	long n = 100;
	System.out.println(fib2(n));
    }
}
