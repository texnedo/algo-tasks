import math

def main():
    a_low = int(input("Enter the lower limit to find prime numbers: "))
    a = int(input("Enter the upper limit to find prime numbers: "))
    max_a = math.sqrt(a)
    primes = [True] * a
    primes[0] = primes[1] = False  # 0 and 1 are not prime numbers
    for i in range(2, int(max_a) + 1):
        if primes[i]:
            for j in range(i * i, a, i):
                primes[j] = False

    for i in range(a):
        if primes[i] and i >= a_low:
            print(i, end=' ')

if __name__ == "__main__":
    main()