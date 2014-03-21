class Sieve {

    private static final int MAX_PRIME = 1000000;

    public static void main(String[] args) {

	char primes[] = new char[MAX_PRIME];

	for (int i = 0; i < MAX_PRIME; i++)
	    primes[i] = 0;

	for (int i = 2; i < MAX_PRIME; i++) {
	    if (primes[i] == 0) {
		System.out.println(i);
		for (int k = i; k < MAX_PRIME; k += i)
		    primes[k] = 1;
	    }
	}

    }

}
