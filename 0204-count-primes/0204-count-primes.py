class Solution(object):
    def countPrimes(self, n):
        if n < 2:
            return 0
        
        # Initialize a boolean array where is_prime[i] indicates if i is prime
        is_prime = [True] * n
        is_prime[0] = is_prime[1] = False
        
        # Sieve of Eratosthenes
        for i in range(2, int(n ** 0.5) + 1):
            if is_prime[i]:
                # Mark multiples of i as not prime
                for j in range(i * i, n, i):
                    is_prime[j] = False
        
        # Count the number of primes
        return sum(is_prime)