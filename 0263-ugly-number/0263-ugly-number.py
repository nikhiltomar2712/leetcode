class Solution(object):
    def isUgly(self, n):
        # Ugly numbers must be positive
        if n <= 0:
            return False
        
        # Divide by 2, 3, 5 as many times as possible
        for prime in [2, 3, 5]:
            while n % prime == 0:
                n //= prime
        
        # If only factors were 2, 3, 5, n will be 1
        return n == 1