package com.texnedo;

public class CountPrimes {
    public static void main(String[] args) {
        CountPrimes primes = new CountPrimes();
        System.out.println(primes.countPrimes(10));
    }

    public int countPrimes(int n) {
        int i = n - 1;
        int count = 0;
        while (i > 0) {
            if (isPrime(i)) {
                count++;
            }
            i--;
        }
        return count;
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        int maxDivisor = (int)Math.sqrt(n);
        int i = 2;
        while (i <= maxDivisor) {
            System.out.println("n = " + n + "i = " + i);
            if (n % i == 0) {
                return false;
            }
            i++;
        }
        System.out.println("prime = " + n);
        return true;
    }
}
